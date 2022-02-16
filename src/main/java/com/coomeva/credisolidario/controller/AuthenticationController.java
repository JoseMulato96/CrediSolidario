package com.coomeva.credisolidario.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coomeva.credisolidario.dto.LoginUser;
import com.coomeva.credisolidario.dto.User;
import com.coomeva.credisolidario.integrations.profileManager.UserVo;
import com.coomeva.credisolidario.service.LoginService;
import com.coomeva.credisolidario.service.ScUsuarioLoginService;
import com.coomeva.credisolidario.utilities.ResponseService;
import com.coomeva.credisolidario.utilities.Status;

/**
 * @Fecha: 08/02/2022 Clase dedicada al proceso de logueo de usuario
 */
@RestController
public class AuthenticationController {
	static final Logger logger = Logger.getLogger(AuthenticationController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private ScUsuarioLoginService scUsuarioLoginService;

	/**
	 * @Fecha: 08/02/2022
	 * @Desc: Valida el usuario en el profile manager
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

		ResponseService response = new ResponseService();
		if (!loginUser.getUsername().equals("") || !loginUser.getPassword().equals("")) {
			try {
				User user = new User();
				user.setUsername(loginUser.getUsername());
				user.setPassword(loginUser.getPassword());

				UserVo userVo = loginService.getUserProfile(loginUser);
				if (userVo != null) {
					if (userVo.getErrorMessage() == null) {
						if (!userVo.getAuthorized().equals("false")) {
							userVo.setIp(loginUser.getIp());
							scUsuarioLoginService.save(userVo);
							response.setStatus(Status.OK);
							response.setData(userVo);
						} else {
							response.setStatus(Status.FAILURE);
							response.setMessageError("El usuario no tiene permisos para esta aplicación");
						}
					} else {
						response.setStatus(Status.FAILURE);
						response.setMessageError(userVo.getErrorMessage());
					}
				} else {
					response.setStatus(Status.FAILURE);
					response.setMessageError("Ocurrio un error al consultar el servicio.");
				}

				return ResponseEntity.ok(response);
			} catch (Exception e) {
				response.setStatus(Status.FAILURE);
				response.setMessageError("Ocurrio un error al consultar el servicio.");
				logger.error("Error Login", e);
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			response.setStatus(Status.FAILURE);
			response.setMessageError("Usuario y contraseña no pueden estar vacios");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
