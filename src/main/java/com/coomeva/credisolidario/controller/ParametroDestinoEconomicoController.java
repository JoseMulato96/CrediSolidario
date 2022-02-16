package com.coomeva.credisolidario.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coomeva.credisolidario.dto.ParametrosDestinoEconomicoDTO;
import com.coomeva.credisolidario.service.ParametroDestinoEconomicoService;
import com.coomeva.credisolidario.utilities.Constants;
import com.coomeva.credisolidario.utilities.ResponseService;
import com.coomeva.credisolidario.utilities.Status;

@RestController
@RequestMapping(value = "api/v1/destinoeconomico")
public class ParametroDestinoEconomicoController {

	static final  Logger logger = Logger.getLogger(ParametroDestinoEconomicoController.class); 
	
	@Autowired
	private ParametroDestinoEconomicoService destinoEconomicoService;
	
	@GetMapping()
	public ResponseEntity<ResponseService> get(){				
		ResponseService response = new ResponseService();
		try {
			response.setData(destinoEconomicoService.getAll());
			response.setStatus(Status.OK);
			return ResponseEntity.ok(response);			
		}catch (Exception e) {
			logger.error(Constants.ERROR_SAVE,e);
			response.setStatus(Status.FAILURE);
			response.setMessageError(Constants.ERROR_SAVE);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<ResponseService> post(@RequestBody ArrayList<ParametrosDestinoEconomicoDTO> items){
		ResponseService response = new ResponseService();
		try {
			response.setData(destinoEconomicoService.add(items));
			response.setStatus(Status.OK);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error(Constants.ERROR_SAVE,e);
			response.setStatus(Status.FAILURE);
			response.setMessageError(Constants.ERROR_SAVE);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping()
	public ResponseEntity<ResponseService> put(@RequestBody List<ParametrosDestinoEconomicoDTO> items){
		ResponseService response = new ResponseService();
		try {
			response.setData(destinoEconomicoService.update(items));
			response.setStatus(Status.OK);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error(Constants.ERROR_SAVE,e);
			response.setStatus(Status.FAILURE);
			response.setMessageError(Constants.ERROR_SAVE);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping()
	public ResponseEntity<ResponseService> delete(@RequestBody Long id){
		ResponseService response = new ResponseService();
		try {
			destinoEconomicoService.delete(id);
			response.setStatus(Status.OK);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error(Constants.ERROR_SAVE,e);
			response.setStatus(Status.FAILURE);
			response.setMessageError(Constants.ERROR_SAVE);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
