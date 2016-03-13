package com.ryan.web.open.mobile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryan.http.HttpResult;
import com.ryan.model.User;
import com.ryan.service.UserService;
import com.ryan.util.ResultCode;
import com.ryan.util.TextUtils;
import com.ryan.web.open.model.OUser;
import com.ryan.web.service.CurrentUser;
/**
 * 和学生相关的api
 */
@Controller
public class StudentApi {
	
	GsonBuilder builder = new GsonBuilder();
	
	@Autowired
	private UserService userService;

	@RequestMapping("/open/getStudentInfo")
	public @ResponseBody String getStudentInfo(){
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		String username = CurrentUser.getUsername();
		if(TextUtils.isEmpty(username)){
			result.setResultCode(ResultCode.NO_LOGIN_USER);
			result.setResultValue("no login user");
			return gson.toJson(result);
		}
		
		List<User> students = userService.getUserByFirstGuardianPhone(username); 
		List<OUser> oList = new ArrayList<OUser>();
		if(students == null || students.size() <= 0){
			result.setResultCode(ResultCode.RESULT_OK);
			result.setResultValue(oList);
			return gson.toJson(result);
		}
		for(User u : students){
			oList.add(buildOuser(u));
		}
		
		result.setResultCode(ResultCode.RESULT_OK);
		result.setResultValue(oList);
		return gson.toJson(result);
	}
	
	private OUser buildOuser(User u){
		OUser ou = new OUser();
		ou.setTelephone(u.getTelephone());
		ou.setUsername(u.getUsername());
		ou.setSex(u.getSex());
		ou.setQq(u.getQq());
		ou.setBirthday(u.getBirthday());
		ou.setEmail(u.getEmail());
		ou.setRfID(u.getRfID());
		ou.setDeviceID(u.getDeviceID());
		return ou;
	}
}
