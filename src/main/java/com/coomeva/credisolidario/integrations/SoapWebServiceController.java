package com.coomeva.credisolidario.integrations;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;


public class SoapWebServiceController/* extends WebServiceGatewaySupport*/{
	
	static final Logger logger = Logger.getLogger(SoapWebServiceController.class);
	private SOAPMessage soapResponse;
	
	public SoapWebServiceController(){
		
	}
		
	public static SoapWebServiceController getInstance(){
		return new SoapWebServiceController();
	}
	

	public boolean callWebService(ParameterSoap param){
		try{
			// Creando conexion SOAP
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConn = soapConnFactory.createConnection();			
			// Enviando mensaje
			soapResponse = soapConn.call(createSOAPRequest(param), param.getServerUri());			
			soapConn.close();	
			return true;
		}catch(Exception e){
			logger.error("Error SOAP:",e);
			return false;
			
		}
	}
	
	private SOAPMessage createSOAPRequest(ParameterSoap param) throws Exception {
		
		try{
			
			MessageFactory messageFactory = MessageFactory.newInstance();
			soapResponse = messageFactory.createMessage();
			SOAPPart soapPart = soapResponse.getSOAPPart();
			
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addNamespaceDeclaration(param.getPrefix(), param.getTargetNamespace());
			
			if (!param.getEncodingStyle().equals("NA"))
				envelope.setEncodingStyle(param.getEncodingStyle());
			
			SOAPBody soapBody = envelope.getBody();			
			SOAPElement soapBodyElem = soapBody.addChildElement(param.getComplexType());	
			
			processSoapElements(soapBodyElem,param);			
						
			MimeHeaders header = soapResponse.getMimeHeaders();
			header.addHeader(param.getHeader(), param.getServerUri());			
			soapResponse.saveChanges();		
			
			
            final StringWriter sw = new StringWriter();

            try {
            	TransformerFactory factory = TransformerFactory.newInstance();
            	factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            	Transformer transformer = factory.newTransformer();
            	transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            	transformer.transform(
                    new DOMSource(soapResponse.getSOAPPart()),
                    new StreamResult(sw));
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }

            // Now you have the XML as a String:
            logger.info("SOAP REQUEST " + sw.toString());
			
			return soapResponse;
			
		}catch(Exception e){	
			logger.error("Error SOAP:",e);
			
			throw new Exception (e);
		}
		
	}
	
	private void processSoapElements(SOAPElement elements, ParameterSoap param) {
		
		try{					
			for(int i=0; i<param.getParametersWebService().size(); i++){				
				SOAPElement tmp = elements.addChildElement(param.getParametersRequest().get(i).toString());
				tmp.addTextNode(param.getParametersWebService().get(i).toString());	
			}			
		}catch(SOAPException e){
			logger.error("Error SOAP:",e);
		}
				
	}

	public SOAPMessage getSoapResponse() {
		return soapResponse;
	}

	public void setSoapResponse(SOAPMessage soapResponse) {
		this.soapResponse = soapResponse;
	}
	
	public String getStringSOAPResponse(SOAPMessage soapResponse) throws Exception{
	       
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		       Transformer transformer;
		       StringWriter writer;
		try {
		transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		writer = new StringWriter();
		       StreamResult result = new StreamResult(writer); 
		       transformer.transform(sourceContent, result);        
		} catch (TransformerConfigurationException e) {	
		throw new TransformerConfigurationException(e);
		} catch (SOAPException e) {
		throw new SOAPException(e);
		} catch (TransformerException e) {
		throw new TransformerException(e);
		}
		            
		return writer.toString();      
	}

	
}
