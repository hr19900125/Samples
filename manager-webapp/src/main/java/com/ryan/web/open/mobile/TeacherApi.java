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
import com.ryan.model.Class;
import com.ryan.service.ClassService;
import com.ryan.service.TeacherService;
import com.ryan.util.ResultCode;
import com.ryan.web.open.model.OClass;
import com.ryan.web.service.CurrentUser;

@Controller
public class TeacherApi {

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ClassService classService;
	
	GsonBuilder builder = new GsonBuilder();
	
	@RequestMapping("/open/getClasses")
	public @ResponseBody String getClassesByTeacherAcc(){
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		
		if(CurrentUser.isParentAccount()){
			result.setResultCode(ResultCode.RESULT_ERROR);
			result.setResultValue("server error");
			return gson.toJson(result);
		}
		String username = CurrentUser.getUsername();
		List<Class> classList = classService.getClassListByTeacherId(username);
		List<OClass> list = new ArrayList<OClass>();
		
		if(classList == null || classList.size() <= 0){
			result.setResultCode(ResultCode.RESULT_OK);
			result.setResultValue(list);
			return gson.toJson(result);
		}
		
	    for(Class c : classList){
	    	list.add(buildOclass(c));
	    }
	    
	    result.setResultCode(ResultCode.RESULT_OK);
	    result.setResultValue(list);
	    return gson.toJson(result);
	}
	
	private OClass buildOclass(Class c){
		OClass oc = new OClass();
		oc.setClassName(c.getClassName());
		oc.setID(c.getID());
		oc.setSchoolID(c.getSchoolID());
		oc.setSchoolName(c.getSchoolName());
		return oc;
	}
	
}
