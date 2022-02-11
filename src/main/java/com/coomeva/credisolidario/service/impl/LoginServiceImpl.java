package com.coomeva.credisolidario.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coomeva.credisolidario.domain.ScPropiedad;
import com.coomeva.credisolidario.dto.LoginUser;
import com.coomeva.credisolidario.integrations.ConsumirWSService;
import com.coomeva.credisolidario.integrations.profileManager.Application;
import com.coomeva.credisolidario.integrations.profileManager.Role;
import com.coomeva.credisolidario.integrations.profileManager.Section;
import com.coomeva.credisolidario.integrations.profileManager.UserVo;
import com.coomeva.credisolidario.repo.ScRepositoryPropiedad;
import com.coomeva.credisolidario.service.LoginService;

@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

	static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	private ConsumirWSService consumirWS;

	@Autowired
	private ScRepositoryPropiedad scRepositoryPropiedad;

	/**	 
	 * @Fecha:  08/02/2022	
	 * @Desc: Consulta y el profile manager y construye el objecto userVo
	 * 
	 * @return UserVo
	 */
	@Override
	public UserVo getUserProfile(LoginUser loginForm) {
		JSONObject userVoObj = null;
		UserVo userVo = null;
		boolean userExist = false;
		ScPropiedad scPropiedad = scRepositoryPropiedad.findByDescripcionIgnoreCase("URL_CONSUMIR_PROFILE_MANAGER");
		try {

			userVoObj = consumirWS.consumirProfileManager(loginForm.getUsername(), loginForm.getPassword(),
					scPropiedad.getValor());
			userVo = new UserVo();

			try {
				userVo.setErrorMessage(userVoObj.getString("faultstring"));
			} catch (Exception e) {
				userExist = true;
			}

			if (userExist) {
				try {
					userVo.setEmployeeId(userVoObj.getString("ax21:employeeId"));
				} catch (Exception e) {
					userVo.setEmployeeId("");
				}
				try {
					userVo.setAuthorized(userVoObj.getBoolean("ax21:authorized") + "");
				} catch (Exception e) {
					userVo.setId("");
				}
				try {
					userVo.setId(String.valueOf(userVoObj.getInt("ax21:id")));
				} catch (Exception e) {
					userVo.setId("");
				}
				try {
					userVo.setLock(userVoObj.getString("ax21:lock"));
				} catch (Exception e) {
					userVo.setLock("");
				}
				try {
					userVo.setMail(userVoObj.getString("ax21:mail"));
				} catch (Exception e) {
					userVo.setMail("");
				}
				try {
					userVo.setMail2(userVoObj.getString("ax21:mail2"));
				} catch (Exception e) {
					userVo.setMail2("");
				}
				try {
					userVo.setName(userVoObj.getString("ax21:name"));
				} catch (Exception e) {
					userVo.setName("");
				}
				try {
					userVo.setTelephone(userVoObj.getString("ax21:telephone"));
				} catch (Exception e) {
					userVo.setTelephone("");
				}
				try {
					userVo.setUserId(userVoObj.getString("ax21:userId"));
				} catch (Exception e) {
					userVo.setUserId("");
				}
				try {
					userVo.setUserType(userVoObj.getString("ax21:userType"));
				} catch (Exception e) {
					userVo.setUserType("");
				}

				List<Application> applications = new ArrayList<Application>();
				try {
					JSONArray applicationsObj = userVoObj.getJSONArray("ax21:applications");

					for (int p = 0; p < applicationsObj.length(); p++) {

						JSONObject appObj = (JSONObject) applicationsObj.get(p);

						JSONArray sectionsObj = appObj.getJSONArray("ax21:sections");

						Application application = new Application();
						List<Section> sections = new ArrayList<Section>();
						for (int i = 0; i < sectionsObj.length(); i++) {
							Section section = new Section();
							JSONObject sect = (JSONObject) sectionsObj.get(i);
							section.setName(sect.getString("ax21:name"));
							List<String> actions = new ArrayList<String>();
							try {
								JSONArray actionsObt = sect.getJSONArray("ax21:actions");
								for (int y = 0; y < actionsObt.length(); y++) {
									actions.add(actionsObt.get(y).toString());
								}
							} catch (Exception eer) {
								actions.add(sect.getString("ax21:actions"));
							}
							section.setActions(actions);
							sections.add(section);
						}
						application.setSections(sections);
						application.setName(appObj.getString("ax21:name"));

						List<Role> roles = new ArrayList<Role>();

						try {

							JSONArray rolesObj = appObj.getJSONArray("ax21:roles");
							for (int m = 0; m < rolesObj.length(); m++) {
								Role role = new Role();
								JSONObject roleObj = (JSONObject) rolesObj.get(m);
								role.setName(roleObj.getString("ax21:name"));
								role.setSections(sections);
								roles.add(role);
							}
						} catch (Exception etr) {

							JSONObject rolesObj = appObj.getJSONObject("ax21:roles");
							Role role = new Role();
							role.setName(rolesObj.getString("ax21:name"));
							role.setSections(sections);
							roles.add(role);
						}

						application.setRoles(roles);

						applications.add(application);
					}

				} catch (Exception e) {

					JSONObject applicationsObj = userVoObj.getJSONObject("ax21:applications");
					JSONObject sectionsObj = applicationsObj.getJSONObject("ax21:sections");
					JSONArray actionObj = sectionsObj.getJSONArray("ax21:actions");
					Application application = new Application();
					Section section = new Section();
					String sect = sectionsObj.getString("ax21:name");
					section.setName(sect);
					List<Section> sections = new ArrayList<Section>();
					List<String> actions = new ArrayList<String>();
					for (int i = 0; i < actionObj.length(); i++) {
						actions.add(actionObj.get(i).toString());

					}
					section.setActions(actions);
					sections.add(section);
					application.setSections(sections);

					List<Role> roles = new ArrayList<Role>();

					try {

						JSONArray rolesObj = applicationsObj.getJSONArray("ax21:roles");
						for (int m = 0; m < rolesObj.length(); m++) {
							Role role = new Role();
							JSONObject roleObj = (JSONObject) rolesObj.get(m);
							role.setName(roleObj.getString("ax21:name"));

							roles.add(role);
						}
					} catch (Exception etr) {

						JSONObject rolesObj = applicationsObj.getJSONObject("ax21:roles");
						Role role = new Role();
						role.setName(rolesObj.getString("ax21:name"));

						roles.add(role);
					}

					application.setRoles(roles);
					applications.add(application);
				}
				userVo.setApplications(applications);
			}

		} catch (Exception e) {
			logger.error("Error al consultar profile manager" + e);
		}
		return userVo;
	}
}
