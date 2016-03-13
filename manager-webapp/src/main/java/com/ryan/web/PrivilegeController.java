package com.ryan.web;

import java.util.List;

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
import com.ryan.model.Privilege;
import com.ryan.service.PrivilegeService;
import com.ryan.service.RolePrivilegeService;
import com.ryan.util.ResultCode;

@Controller
public class PrivilegeController {

	GsonBuilder builder = new GsonBuilder(); 
	@Autowired
	PrivilegeService privilegeService;
	@Autowired
	RolePrivilegeService rolePrivilegeService;
	
	@RequestMapping("permissions.htm")
	public ModelAndView showPrivilegeList(){
		ModelAndView mv = new ModelAndView();
		List<Privilege> privilegeList = privilegeService.getAllPrivilege();
		mv.setViewName("permissions");
		mv.addObject("privilegeList", privilegeList);
		return mv;
	}
	
	@RequestMapping(value="savePrivilege.do",method = RequestMethod.POST)
	public @ResponseBody String savePrivilege(@RequestParam(value="pid" ,defaultValue="0")long pid,
			@RequestParam(value="ptype" ,defaultValue="")String privilegeType,
			@RequestParam(value="pd",defaultValue="")String privilegeDescr,
			@RequestParam(value="au",defaultValue="")String actionUrl){
		Gson gson = builder.create();
		String body = "";
		if(pid <= 0){
			//创建
			privilegeService.insertPrivilege(privilegeType, privilegeDescr, actionUrl);
		}else{
			//编辑
			Privilege p = privilegeService.getPrivilege(pid);
			p.setPrivilegeType(privilegeType);
			p.setPrivilegeDescr(privilegeDescr);
			p.setPrivilegeAction(actionUrl);
			privilegeService.updatePrivilege(p);
		}
		
		HttpResult result = new HttpResult();
		result.setResultCode(ResultCode.RESULT_OK);
		result.setResultValue(null);
		body = gson.toJson(result);
		return body;
	}
	
	@RequestMapping(value="deletePrivilege.do",method = RequestMethod.POST)
	public @ResponseBody String deletePrivilege(@RequestParam(value="pid",defaultValue="0")long pid){
		String body = "";
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		if(pid>0){
		   privilegeService.deletePrivilege(pid);
		   result.setResultCode(ResultCode.RESULT_OK);
		}else{
		   result.setResultCode(ResultCode.RESULT_ERROR);
		}
		body = gson.toJson(result);
		return body;
	}

	
}
