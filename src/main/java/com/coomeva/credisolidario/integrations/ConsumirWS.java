package com.coomeva.credisolidario.integrations;

import java.io.Serializable;

import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.coomeva.credisolidario.utilities.Constants;

@Service(value = "ConsumirWSService")
public class ConsumirWS implements Serializable, ConsumirWSService {

	private static final long serialVersionUID = 65L;
	private SoapWebServiceController SoapWSC = new SoapWebServiceController();
	private final Logger logger = Logger.getLogger(ConsumirWS.class);

	/**
	 * @Fecha: 08/02/2022		
	 * @Desc: Obtener los datos del profile manager
	 * 
	 * @param userName
	 * @param password
	 * 
	 * @return jsonObject
	 */
	@Override
	public JSONObject consumirProfileManager(String userName, String password, String url) {
		ParameterSoap wsdlData = new ParameterSoap();

		String directory = Constants.DIRECTORY_APP_PROFILE;
		String applicacionBuscada = Constants.NAME_APP_PROFILE;
		String validaAplicacion = Constants.VALIDATE_APP_PROFILE;

		wsdlData.addParameterWebService(0, directory);
		wsdlData.addParameterWebService(1, userName);
		wsdlData.addParameterWebService(2, password);
		wsdlData.addParameterWebService(3, validaAplicacion);
		wsdlData.addParameterWebService(4, applicacionBuscada);
		wsdlData.addParameterRequest(0, "directory");
		wsdlData.addParameterRequest(1, "userName");
		wsdlData.addParameterRequest(2, "password");
		wsdlData.addParameterRequest(3, "setaplicacionBuscada");
		wsdlData.addParameterRequest(4, "app");

		wsdlData.setServerUri(url);
		wsdlData.setComplexType("ges:validateUserApp");
		wsdlData.setHeader("SOAPAction");
		wsdlData.setPrefix("ges");
		wsdlData.setTargetNamespace("http://geside.coomeva.com.co");
		wsdlData.setEncodingStyle("NA");

		SoapWSC.callWebService(wsdlData);
		SOAPMessage rsXML = SoapWSC.getSoapResponse();
		String response = "";
		try {
			response = SoapWSC.getStringSOAPResponse(rsXML);
		} catch (Exception e) {
			logger.error("error consumir servicio profile manager", e);
		}
		JSONObject jsonObject = null;
		JSONObject jsonObject2 = null;
		JSONObject jsonBody = null;
		try {
			jsonObject = XML.toJSONObject(response);
			jsonBody = jsonObject.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
					.getJSONObject("ns:validateUserAppResponse").getJSONObject("ns:return");
		} catch (JSONException e) {
			logger.error("El usuario no existe en el profile manager", e);

			try {
				jsonObject2 = XML.toJSONObject(response);
				jsonBody = jsonObject2.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body")
						.getJSONObject("soapenv:Fault");
			} catch (JSONException e1) {
				logger.error("error consumir servicio profile manager", e1);
			}

		}
		return jsonBody;
	}

}