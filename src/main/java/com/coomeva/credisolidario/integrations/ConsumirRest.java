package com.coomeva.credisolidario.integrations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ConsumirRest implements IConsumirRest {

	static final Logger logger = Logger.getLogger(ConsumirRest.class);

	@Value("${urlValidar}")
	private String urlValidar;
	
}
