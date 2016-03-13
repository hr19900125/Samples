package com.ryan.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryan.http.HttpResult;
import com.ryan.model.ParentAccount;
import com.ryan.model.TeacherAccount;
import com.ryan.service.ParentAccountService;
import com.ryan.service.TeacherService;
import com.ryan.util.MD5;
import com.ryan.util.ResultCode;
import com.ryan.util.WebConstant;
import com.ryan.web.service.CurrentUser;

@Controller
public class AccountController {

	@Autowired
    ParentAccountService parentAccountService;
	
	@Autowired
	TeacherService teacherService;
	
	private GsonBuilder mBuilder = new GsonBuilder();
	
	@RequestMapping("accEdit.htm")
	public ModelAndView showAccountEdit(){
		ModelAndView mv = new ModelAndView();
		if(CurrentUser.isParentAccount()){
		    //是家长账户
			String username = CurrentUser.getUsername();
			ParentAccount account = parentAccountService.getParentAccount(username);
			mv.setViewName("parentAccountEdit");
			mv.addObject("account", account);
		}else if(CurrentUser.isTeacherAccount()){
			//是教师账户
			String username = CurrentUser.getUsername();
			TeacherAccount account = teacherService.getTeacherAccountByUsername(username);
			mv.setViewName("teacherAccountEdit");
			mv.addObject("account", account);
		}
		
		return mv;
	}
	
	@RequestMapping(value="editPassword.do" , method=RequestMethod.POST)
	public @ResponseBody String editPassword(@RequestParam(value="op",required=true)String oldPasswd,
			@RequestParam(value="np",required=true)String newPasswd){
		Gson gson = mBuilder.create();
		
		HttpResult result = new HttpResult();
		
        if(CurrentUser.isParentAccount()){
        	String username = CurrentUser.getUsername();
        	ParentAccount account = parentAccountService.getParentAccount(username);
        	if(account.getPassword().equals(MD5.encodePassword(oldPasswd, WebConstant.PASSWD_MD5_KEY))){
        		account.setPassword(MD5.encodePassword(newPasswd,WebConstant.PASSWD_MD5_KEY));
        		boolean flag = parentAccountService.updateParentAccount(account);
        		if(flag){
        			result.setResultCode(ResultCode.RESULT_OK);
        			return gson.toJson(result);
        		}else{
        			result.setResultCode(ResultCode.RESULT_ERROR);
        			return gson.toJson(result);
        		}
        		
        	}else{
        		result.setResultCode(ResultCode.RESULT_ERROR);
        		return gson.toJson(result);
        	}
        	
        }else if(CurrentUser.isTeacherAccount()){
        	
        	String username = CurrentUser.getUsername();
        	TeacherAccount account = teacherService.getTeacherAccountByUsername(username);
        	if(account.getPassword().equals(MD5.encodePassword(oldPasswd, WebConstant.PASSWD_MD5_KEY))){
        		account.setPassword(MD5.encodePassword(newPasswd,WebConstant.PASSWD_MD5_KEY));
        		boolean flag = teacherService.updateTeacherAccount(account);
        		if(flag){
        			result.setResultCode(ResultCode.RESULT_OK);
        			return gson.toJson(result);
        		}else{
        			result.setResultCode(ResultCode.RESULT_ERROR);
        			return gson.toJson(result);
        		}
        	}else{
        		result.setResultCode(ResultCode.RESULT_ERROR);
        		return gson.toJson(result);
        	}
        }
		return "";
	}
	
}
