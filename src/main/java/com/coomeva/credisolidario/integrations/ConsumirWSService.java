package com.coomeva.credisolidario.integrations;

import org.json.JSONObject;

public interface ConsumirWSService {

	public JSONObject consumirProfileManager(String userName, String password, String url);
}
