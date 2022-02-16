package com.coomeva.credisolidario.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coomeva.credisolidario.service.ParametrosBaseService;
import com.coomeva.credisolidario.utilities.Constants;
import com.coomeva.credisolidario.utilities.ResponseService;
import com.coomeva.credisolidario.utilities.Status;

@RestController
public class ParametrosBaseController {

	static final Logger logger = Logger.getLogger(ParametrosBaseController.class);

	@Autowired
	private ParametrosBaseService parametrosBaseService;

	@RequestMapping(value = "parametrosbase", method = RequestMethod.GET)
	public ResponseEntity<ResponseService> getParametrosBase() {
		ResponseService response = new ResponseService();
		try {
			response.setData(parametrosBaseService.getAll());
			response.setStatus(Status.OK);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			logger.error(Constants.ERROR_SAVE, e);
			response.setStatus(Status.FAILURE);
			response.setMessageError(Constants.ERROR_SAVE);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
