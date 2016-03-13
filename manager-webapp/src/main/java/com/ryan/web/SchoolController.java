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
import com.ryan.service.ClassService;
import com.ryan.service.SchoolService;
import com.ryan.util.Pagination;
import com.ryan.util.ResultCode;

@Controller
public class SchoolController {

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private ClassService classService;
	
	GsonBuilder builder = new GsonBuilder();  
	
	@RequestMapping("schools.htm")
	public ModelAndView showSchoolListPage(@RequestParam(value = "p", defaultValue = "1") int page,
			@RequestParam(value = "s", defaultValue = "") String search,
			HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("schools");
		Pagination p = new Pagination();
		p.setPage(page);
		p.setPerPageSize(5);
		List<School> schoolList = schoolService.getSchoolListByPage(p);
		mv.addObject("schoolList", schoolList);
		mv.addObject("p", p);
		return mv;
	}
	
	@RequestMapping("schoolEdit.htm")
	public ModelAndView showSchoolEditPage(@RequestParam(value="sid",defaultValue="0")long sid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("schoolEdit");
		
		if(sid > 0){
			School school = schoolService.getSchoolById(sid);
			mv.addObject("school", school);
			mv.addObject("isEdit", true);
		}else{
			mv.addObject("isEdit",false);
		}
		mv.addObject("schoolID", sid);
		
		return mv;
	}

	@RequestMapping(value="saveSchool.do" , method = RequestMethod.POST)
	public @ResponseBody String saveSchool(@RequestParam(value="sid",defaultValue="0")long sid,
			                    @RequestParam(value="schoolName",defaultValue="")String schoolName,
			                    @RequestParam(value="provinceID",defaultValue="")String provinceID,
			                    @RequestParam(value="cityID",defaultValue="")String cityID,
			                    @RequestParam(value="countyID",defaultValue="")String countyID,
			                    @RequestParam(value="region",defaultValue="")String region){
		String body = "";
		Gson gson = builder.create();
	    School school = null;
	    if(sid <= 0){
	    	school = new School();
	    }else{
	    	school = schoolService.getSchoolById(sid);
	    }
	    
	    school.setSchoolName(schoolName);
	    school.setProvinceID(provinceID);
	    school.setCityID(cityID);
	    school.setCountyID(countyID);
	    school.setRegion(region);
	    if(sid > 0){
	    	school.setModifyTime(new Date());
	    	schoolService.udpateSchool(school);
	    }else{
	    	school.setCreateTime(new Date());
	    	schoolService.addSchool(school);
	    }
        
	    HttpResult result = new HttpResult();
	    result.setResultCode(ResultCode.RESULT_OK);
	    result.setResultValue(sid);
	    body = gson.toJson(result);
		return body;
	}
	
	@RequestMapping(value="deleteSchool.do" , method = RequestMethod.POST)
	public @ResponseBody String deleteSchool(@RequestParam(value="sid",defaultValue = "0")long sid){
		String body = "";
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		boolean flag = false;
		if(sid > 0){
			flag = schoolService.deleteSchool(sid);
		}
		
		if(flag){
			classService.setClassDeleted(sid);
			result.setResultCode(ResultCode.RESULT_OK);
		}else{
			result.setResultCode(ResultCode.RESULT_ERROR);
		}
		
		body = gson.toJson(result);
		return body;
	}
}
