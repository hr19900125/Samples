package com.ryan.web;

import java.util.Date;
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
import com.ryan.model.Class;
import com.ryan.model.School;
import com.ryan.service.ClassService;
import com.ryan.service.SchoolService;
import com.ryan.util.Pagination;
import com.ryan.util.ResultCode;

@Controller
public class ClassController {

	@Autowired
	private ClassService classService;
	
	@Autowired
	private SchoolService schoolService;
	
	GsonBuilder builder = new GsonBuilder();
	
	@RequestMapping("classes.htm")
	public ModelAndView showClassListPage(@RequestParam(value = "p", defaultValue = "1") int page,
			@RequestParam(value = "s", defaultValue = "") String search){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("classes");
		Pagination p = new Pagination();
		p.setPage(page);
		p.setPerPageSize(5);
		List<Class> classList = classService.getClassListByPage(p);
		mv.addObject("classList", classList);
		mv.addObject("p", p);
		return mv;
	}
	
	@RequestMapping("classEdit.htm")
	public ModelAndView showClassEditPage(@RequestParam(value="cid",defaultValue="0")long cid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("classEdit");
		
		List<School> schoolList = schoolService.getAllSchool();
		
		if(cid > 0){
			Class c = classService.getClassById(cid);
			mv.addObject("cls", c);
			mv.addObject("isEdit", true);
		}else{
			mv.addObject("isEdit", false);
		}
		mv.addObject("schoolList", schoolList);
		mv.addObject("classID", cid);
		return mv;
	}
	
	@RequestMapping(value="saveClass.do" ,method=RequestMethod.POST)
	public @ResponseBody String saveClass(@RequestParam(value="cid",defaultValue="0")long cid,
			@RequestParam(value="className",defaultValue="")String className,
			@RequestParam(value="schoolId",defaultValue="0")long schoolId,
			@RequestParam(value="schoolName",defaultValue="")String schoolName){
		String body = "";
		Gson gson = builder.create();
		Class cls = null;
		if(cid <= 0){
			cls = new Class();
		}else{
			cls = classService.getClassById(cid);
		}
		
		cls.setClassName(className);
		cls.setSchoolID(schoolId);
		cls.setSchoolName(schoolName);
		cls.setModifyTime(new Date());
		if(cid <= 0){
			cls.setCreateTime(new Date());
		}
		
		HttpResult result = new HttpResult();
		result.setResultCode(ResultCode.RESULT_OK);
		result.setResultValue(cid);
		body = gson.toJson(result);
		return body;
	}
	
	@RequestMapping(value="deleteClass.do" , method=RequestMethod.POST)
	public @ResponseBody String deleteClass(@RequestParam(value="cid")long cid){
		String body = "";
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		boolean flag = false;
		if(cid > 0){
			flag = classService.deleteClass(cid);
		}
		
		if(flag){
			result.setResultCode(ResultCode.RESULT_OK);
		}else{
			result.setResultCode(ResultCode.RESULT_ERROR);
		}
		
		body = gson.toJson(result);
		return body;
	}
	
	@RequestMapping(value="getClassesOfSchool.do",method=RequestMethod.POST)
	public @ResponseBody String getClassesOfSchool(@RequestParam("sid")long schoolID){
		Gson gson = builder.create();
		List<Class> list = classService.getClassListBySchoolId(schoolID);
		HttpResult result = new HttpResult();
		if(list == null){
			result.setResultCode(ResultCode.RESULT_ERROR);
	        return gson.toJson(result);
		}
		
		result.setResultCode(ResultCode.RESULT_OK);
		result.setResultValue(list);
		return gson.toJson(result);
	}
}
