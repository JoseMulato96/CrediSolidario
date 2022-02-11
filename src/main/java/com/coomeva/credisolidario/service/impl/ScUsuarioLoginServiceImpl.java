package com.coomeva.credisolidario.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coomeva.credisolidario.domain.ScUsuarioLogin;
import com.coomeva.credisolidario.integrations.profileManager.UserVo;
import com.coomeva.credisolidario.repo.ScUsuarioLoginRepository;
import com.coomeva.credisolidario.service.ScUsuarioLoginService;

@Service(value = "ScUsuarioLoginService")
public class ScUsuarioLoginServiceImpl implements ScUsuarioLoginService {

	@Autowired
	private ScUsuarioLoginRepository scUsuarioLoginRepository;

	/** 
	 * @Fecha: 29/10/2018
	 * @Desc: Metodo para guardar el logeo de usuario
	 * @param data type FcpAutorizacion object
	 * 
	 * @return ScUsuarioLogin
	 * 
	 */
	@Override
	public ScUsuarioLogin save(UserVo userVo) {

		ScUsuarioLogin user = new ScUsuarioLogin();
		user.setIpEquipo(userVo.getIp());
		user.setFechaIngreso(new Date());
		user.setUsuarioIngreso(userVo.getUserId());
		return scUsuarioLoginRepository.save(user);
	}

}
