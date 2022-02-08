package com.coomeva.credisolidario.integrations;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "ConsumirWSService")
public class ConsumirWS implements Serializable, ConsumirWSService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 65L;

	private final Logger logger = Logger.getLogger(ConsumirWS.class);

//	@Value("${urlservicioBanco}")
	private String urlServiceBanco;
	

}