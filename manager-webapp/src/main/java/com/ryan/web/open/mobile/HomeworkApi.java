package com.ryan.web.open.mobile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryan.http.HttpResult;
import com.ryan.model.Homework;
import com.ryan.service.HomeworkService;
import com.ryan.util.ResultCode;
import com.ryan.web.open.model.OHomework;

@Controller
public class HomeworkApi {
	
	@Autowired
	private HomeworkService homeworkService;
	
	GsonBuilder builder = new GsonBuilder();

	@RequestMapping("/open/getHomeworks")
	public @ResponseBody String getHomeworkListByClassId(@RequestParam(value="classId",defaultValue="-1")long classId){
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		
		if(classId == -1){
			result.setResultCode(ResultCode.ILLEGAL_PARAMETER);
			result.setResultValue("illegal parameter");
			return gson.toJson(result);
		}
		
		List<Homework> homeworkList = homeworkService.getHomeworkListByClassId(classId);
		List<OHomework> list = new ArrayList<OHomework>();
		if(homeworkList == null || homeworkList.size() <= 0){
			result.setResultValue(ResultCode.RESULT_OK);
			result.setResultValue(list);
			return gson.toJson(result);
		}
		
		for(Homework h : homeworkList){
			list.add(buildOHomework(h));
		}
		result.setResultCode(ResultCode.RESULT_OK);
		result.setResultValue(list);
		return gson.toJson(result);
	}
	
	private OHomework buildOHomework(Homework work){
		OHomework oh = new OHomework();
		oh.setID(work.getID());
		oh.setTitle(work.getTitle());
		oh.setContent(work.getContent());
		oh.setCreatorName(work.getCreatorName());
		oh.setCreateTime(work.getCreateTime());
		return oh;
	}
	
}
