package com.coomeva.credisolidario.service;

import com.coomeva.credisolidario.integrations.profileManager.UserVo;
import com.coomeva.credisolidario.dto.LoginUser;

public interface LoginService {
	UserVo getUserProfile(LoginUser loginForm);
}
