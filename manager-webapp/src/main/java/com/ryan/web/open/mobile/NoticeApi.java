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
import com.ryan.model.Notice;
import com.ryan.service.NoticeService;
import com.ryan.util.ResultCode;
import com.ryan.web.open.model.ONotice;

@Controller
public class NoticeApi {

	GsonBuilder builder = new GsonBuilder();
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/open/getClassNotice")
	public @ResponseBody String getClassNotice(@RequestParam(value="classId",defaultValue="-1")int classId){
		
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		
		if(classId == -1){
			result.setResultCode(ResultCode.ILLEGAL_PARAMETER);
			result.setResultValue("illegal parameter");
			return gson.toJson(result);
		}
		
		List<Notice> noticeList = noticeService.getClassNoticeList(classId);
		List<ONotice> list = new ArrayList<ONotice>();
		if(noticeList == null || noticeList.size() <= 0){
			result.setResultCode(ResultCode.RESULT_OK);
			result.setResultValue(list);
			return gson.toJson(result);
		}
		
		for(Notice n : noticeList){
			list.add(buildONotice(n));
		}
		
		result.setResultCode(ResultCode.RESULT_OK);
		result.setResultValue(list);
		return gson.toJson(result);
	}
	
	@RequestMapping("/open/getSchoolNotice")
	public @ResponseBody String getSchoolNotice(@RequestParam(value="schoolId",defaultValue="-1")int schoolId){
		
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		
		if(schoolId == -1){
			result.setResultCode(ResultCode.ILLEGAL_PARAMETER);
			result.setResultValue("illegal parameter");
			return gson.toJson(result);
		}
		
		List<Notice> noticeList = noticeService.getSchoolNoticeList(schoolId);
		List<ONotice> list = new ArrayList<ONotice>();
		if(noticeList == null || noticeList.size() <= 0){
			result.setResultCode(ResultCode.RESULT_OK);
			result.setResultValue(list);
			return gson.toJson(result);
		}
		
		for(Notice n : noticeList){
			list.add(buildONotice(n));
		}
		
		result.setResultCode(ResultCode.RESULT_OK);
		result.setResultValue(list);
		return gson.toJson(result);
	}
	
	private ONotice buildONotice(Notice n){
		ONotice notice = new ONotice();
		notice.setID(n.getID());
		notice.setTitle(n.getTitle());
		notice.setContent(n.getContent());
		notice.setCreatorName(n.getCreatorName());
		notice.setCreateTime(n.getCreateTime());
		notice.setType(n.getType());
		
		return notice;
	}
	
}
