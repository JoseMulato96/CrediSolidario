package com.coomeva.credisolidario.controller;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.coomeva.credisolidario.service.LoginService;

/**
 * Clase dedicada al proceso de creación del token
 * */
@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	@Qualifier("loginService")
	private LoginService loginService;

	static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		String name = authentication.getName();
		// You can get the password here
		String password = authentication.getCredentials().toString();

		try {
			
			return  new UsernamePasswordAuthenticationToken(name, password,
					Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

		} catch (Exception e) {
			logger.error("No se pudo crear el token", e);
			return null;
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
