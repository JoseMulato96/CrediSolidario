package com.coomeva.credisolidario.integrations;

import java.util.TreeMap;

public class ParameterSoap {

	private TreeMap<Integer, String> parametersRequest = null;
	private TreeMap<Integer, String> parametersWebService = null;
	private String response;
	private String serverUri;
	private String prefix;
	private String targetNamespace;
	private String complexType;
	private String header;
	private String encodingStyle;

	public ParameterSoap() {
		parametersRequest = new TreeMap<Integer, String>();
		parametersWebService = new TreeMap<Integer, String>();
	}

	public TreeMap<Integer, String> getParametersRequest() {
		return parametersRequest;
	}

	public void setParametersRequest(TreeMap<Integer, String> parametersRequest) {
		this.parametersRequest = parametersRequest;
	}

	public void clearParameterRqs() {
		parametersRequest.clear();
	}

	public void clearParameterWsc() {
		parametersWebService.clear();
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public TreeMap<Integer, String> getParametersWebService() {
		return parametersWebService;
	}

	public void setParametersWebService(TreeMap<Integer, String> parametersWebService) {
		this.parametersWebService = parametersWebService;
	}

	public void addParameterRequest(Integer key, String value) {
		parametersRequest.put(key, value);
	}

	public void addParameterWebService(Integer key, String value) {
		parametersWebService.put(key, value);
	}

	public String getServerUri() {
		return serverUri;
	}

	public void setServerUri(String serverUri) {
		this.serverUri = serverUri;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getTargetNamespace() {
		return targetNamespace;
	}

	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	public String getComplexType() {
		return complexType;
	}

	public void setComplexType(String complexType) {
		this.complexType = complexType;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getEncodingStyle() {
		return encodingStyle;
	}

	public void setEncodingStyle(String encodingStyle) {
		this.encodingStyle = encodingStyle;
	}

}