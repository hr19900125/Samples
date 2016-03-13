package com.ryan.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ryan.model.School;
import com.ryan.model.User;
import com.ryan.model.UserQuery;
import com.ryan.service.SchoolService;
import com.ryan.service.UserService;
import com.ryan.util.DateUtil;
import com.ryan.util.Pagination;
import com.ryan.util.ResultCode;
import com.ryan.util.TextUtils;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	SchoolService schoolService;
	
	GsonBuilder builder = new GsonBuilder();   

	@RequestMapping("users.htm")
	public ModelAndView showUserList(@RequestParam(value = "p", defaultValue = "1") int page,
			@RequestParam(value = "s", defaultValue = "") String search,
			HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		Pagination p = new Pagination();
		p.setPage(page);
		if(TextUtils.isEmpty(search)){
			search = null;
		}
		List<User> userList = userService.getUserListByPage(UserQuery.SORT_BY_ID, p);
		mv.setViewName("users");
		mv.addObject("userList", userList);
		mv.addObject("p", p);
		return mv;
	}

	
	@RequestMapping("user.htm")
	public ModelAndView showUser(@RequestParam(value = "uid")String uid){
		ModelAndView mv = new ModelAndView();
		if(TextUtils.isEmpty(uid)){
			//uid不对
			
		}else{
			User user = userService.getUser(uid);
			mv.setViewName("user");
			mv.addObject("user", user);
		}
		return mv;
	}
	
	@RequestMapping("userEdit.htm")
	public ModelAndView showUserEdit(@RequestParam(value="uid",defaultValue="")String uid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userEdit");
		mv.addObject("userID", uid);
		
		List<School> schoolList = schoolService.getAllSchool();
		mv.addObject("schoolList", schoolList);
		
		if(!TextUtils.isEmpty(uid)){
			//编辑
			User user = userService.getUser(uid);
			mv.addObject("user", user);
			mv.addObject("isEdit", true);
		}else{
			//创建
			mv.addObject("isEdit", false);
		}
		return mv;
	}
	
	@RequestMapping(value="saveUser.do",method = RequestMethod.POST)
	public @ResponseBody String saveUser(@RequestParam(value="uid",defaultValue="")String uid,
			@RequestParam(value="username",defaultValue="")String userName,
			@RequestParam(value="birthday",defaultValue="")String birthdayTime,
			@RequestParam(value="sex",defaultValue="")String sex,
			@RequestParam(value="email",defaultValue="")String email,
			@RequestParam(value="qq",defaultValue="")String qq,
			@RequestParam(value="deviceID",defaultValue="")String deviceID,
			@RequestParam(value="rfID",defaultValue="")String rfID,
			@RequestParam(value="firstGuardianPhone",defaultValue="")String firstGuardianPhone,
			@RequestParam(value="secondGuardianPhone",defaultValue="")String secondGuardianPhone,
			@RequestParam(value="thirdGuardianPhone",defaultValue="")String thirdGuardianPhone,
			@RequestParam(value="schoolID")long schoolID,
			@RequestParam(value="schoolName")String schoolName,
			@RequestParam(value="classID")long classID,
			@RequestParam(value="className")String className){
		
		String body = "";
		Gson gson = builder.create();
		User user = null;
		if(TextUtils.isEmpty(uid)){
			user = new User();
		}else{
			user = userService.getUser(uid);
		}
		Date birthday = DateUtil.parse(birthdayTime, "yyyy-MM-dd");
		user.setTelephone(uid);
		user.setUsername(userName);
		user.setBirthday(birthday);
		user.setSex(sex);
		user.setEmail(email);
		user.setQq(qq);
		user.setDeviceID(deviceID);
		user.setRfID(rfID);
		Date nowTime = new Date();
		user.setModifyTime(nowTime);
		user.setSchoolID(schoolID);
		user.setSchoolName(schoolName);
		user.setClassID(classID);
		user.setClassName(className);
		
		if(TextUtils.isEmpty(uid)){
			user.setCreateTime(nowTime);
			userService.insertUser(user);
		}else{
		   userService.updateUser(user);
		}
		
		HttpResult result = new HttpResult();
		result.setResultCode(ResultCode.RESULT_OK);
		result.setResultValue(uid);
		body = gson.toJson(result);
		return body;
	}
	
	@RequestMapping(value="deleteUser.do",method = RequestMethod.POST)
	public @ResponseBody String deleteUser(@RequestParam(value="uid",defaultValue = "")String uid){
		String body = "";
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		boolean flag = false;
		if(!TextUtils.isEmpty(uid)){
			boolean ret = userService.deleteUser(uid);
			if(ret){
				flag = true;
				result.setResultCode(ResultCode.RESULT_OK);
			}
		}
		
		if(!flag){
			result.setResultCode(ResultCode.RESULT_ERROR);
		}
		body = gson.toJson(result);
		return body;
	}
}
