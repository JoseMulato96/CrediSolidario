package com.coomeva.credisolidario.service;

import com.coomeva.credisolidario.integrations.profileManager.UserVo;
import com.coomeva.credisolidario.domain.ScUsuarioLogin;

public interface ScUsuarioLoginService {
	
	ScUsuarioLogin save(UserVo userVo);
}
