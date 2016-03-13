package com.ryan.web.open.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryan.http.HttpResult;
import com.ryan.model.ParentAccount;
import com.ryan.model.TeacherAccount;
import com.ryan.service.ParentAccountService;
import com.ryan.service.TeacherService;
import com.ryan.util.BizResultCode;
import com.ryan.util.MD5;
import com.ryan.util.ResultCode;
import com.ryan.util.TextUtils;
import com.ryan.util.WebConstant;
import com.ryan.web.open.model.OParentAccount;
import com.ryan.web.open.model.OTeacherAccount;
import com.ryan.web.service.CurrentUser;

/**
 * 使用oauth 2.0 
 */
@Controller
public class UserApi {

	GsonBuilder builder = new GsonBuilder();
	
	@Autowired
	private ParentAccountService parentAccountService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("/open/getUserInfo")
	public @ResponseBody String getUserInfo(){
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		
		String username = CurrentUser.getUsername();
		
		if(TextUtils.isEmpty(username)){
			result.setResultCode(ResultCode.ILLEGAL_PARAMETER);
			result.setResultValue("request parameter illegal error");
			return gson.toJson(result);
		}
		
		System.out.println("useType"+CurrentUser.getUsername());
		
		if(CurrentUser.isParentAccount()){
			ParentAccount acc = parentAccountService.getParentAccount(username);
			if(acc == null){
				result.setResultCode(BizResultCode.USER_GET_ERROR);
				result.setResultValue("get user error[not found user]");
				return gson.toJson(result);
			}else{
				OParentAccount oacc = new OParentAccount();
				oacc.setUsername(acc.getParentName());
				oacc.setUserType(WebConstant.PARENT_LOGIN);
				
				result.setResultCode(ResultCode.RESULT_OK);
				result.setResultValue(oacc);
				return gson.toJson(result);
			}
		}else{
			TeacherAccount acc = teacherService.getTeacherAccountByUsername(username);
			if(acc == null){
				result.setResultCode(BizResultCode.USER_GET_ERROR);
				result.setResultValue("get user error[not found user]");
				return gson.toJson(result);
			}else{
				OTeacherAccount oacc = new OTeacherAccount();
				oacc.setSchoolID(acc.getSchoolID());
				oacc.setSchoolName(acc.getSchoolName());
				oacc.setTeacherName(acc.getTeacherName());
				oacc.setUserType(WebConstant.TEACHER_LOGIN);
				return gson.toJson(result);
			}
		}
	}
	
	@RequestMapping("/open/changePassword")
	public @ResponseBody String changePassword(
			@RequestParam(value="oldpsw",defaultValue="")String oldPassword,
			@RequestParam(value="newpsw",defaultValue="")String newPassword){
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		if(TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword)){
			result.setResultCode(ResultCode.ILLEGAL_PARAMETER);
			result.setResultValue("request parameter illegal error");
			return gson.toJson(result);
		}
		
		String username = CurrentUser.getUsername();
		
		if(CurrentUser.isParentAccount()){
			ParentAccount acc = parentAccountService.getParentAccount(username);
			if(acc == null){
				result.setResultCode(BizResultCode.USER_GET_ERROR);
				result.setResultValue("not find user");
				return gson.toJson(result);
			}else{
				
				if(!MD5.encodePassword(oldPassword, WebConstant.PASSWD_MD5_KEY).equals(acc.getPassword())){
					result.setResultCode(BizResultCode.OLD_PASSWORD_WROING);
					result.setResultValue("password error");
					return gson.toJson(result);
				}else{
					acc.setPassword(MD5.encodePassword(newPassword, WebConstant.PASSWD_MD5_KEY));
					boolean flag = parentAccountService.updateParentAccount(acc);
					if(!flag){
						result.setResultCode(BizResultCode.CHANGE_PASSWORD_ERROR);
						result.setResultValue("change password error");
						return gson.toJson(result);
					}else{
						result.setResultCode(ResultCode.RESULT_OK);
						result.setResultValue("ok");
						return gson.toJson(result);
					}
				}
			}
			
		}else{
			TeacherAccount acc = teacherService.getTeacherAccountByUsername(username);
			if(acc == null){
				result.setResultCode(BizResultCode.USER_GET_ERROR);
				result.setResultValue("not find user");
				return gson.toJson(result);
			}else{
				
				if(!MD5.encodePassword(oldPassword, WebConstant.PASSWD_MD5_KEY).equals(acc.getPassword())){
					result.setResultCode(BizResultCode.OLD_PASSWORD_WROING);
					result.setResultValue("password error");
					return gson.toJson(result);
				}else{
					acc.setPassword(MD5.encodePassword(newPassword, WebConstant.PASSWD_MD5_KEY));
					boolean flag = teacherService.updateTeacherAccount(acc);
					if(!flag){
						result.setResultCode(BizResultCode.CHANGE_PASSWORD_ERROR);
						result.setResultValue("change password error");
						return gson.toJson(result);
					}else{
						result.setResultCode(ResultCode.RESULT_OK);
						result.setResultValue("ok");
						return gson.toJson(result);
					}
				}
			}
			
		}
	}
	
}
