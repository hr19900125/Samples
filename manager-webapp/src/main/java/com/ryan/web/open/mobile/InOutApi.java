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
import com.ryan.model.InOutRecord;
import com.ryan.service.InOutRecordService;
import com.ryan.util.ResultCode;
import com.ryan.util.TextUtils;
import com.ryan.web.open.model.OInOutRecord;

@Controller
public class InOutApi {
	
	@Autowired
	private InOutRecordService inOutRecordService;
	
	GsonBuilder builder = new GsonBuilder();

	@RequestMapping("/open/getInOutRecord")
	public @ResponseBody String getInOutRecord(@RequestParam(value="studentTel",defaultValue="")String studentTel){
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		if(TextUtils.isEmpty(studentTel)){
			result.setResultCode(ResultCode.ILLEGAL_PARAMETER);
			result.setResultValue("illegal parameter");
			return gson.toJson(result);
		}
		
		List<InOutRecord> inOutList = inOutRecordService.getStudentInOutRecord(studentTel);
		List<OInOutRecord> list = new ArrayList<OInOutRecord>();
		
		if(inOutList == null || inOutList.size() <= 0){
			result.setResultCode(ResultCode.RESULT_OK);
			result.setResultValue(list);
			return gson.toJson(result);
		}
		
		for(InOutRecord record : inOutList){
			list.add(buildOInOutRecord(record));
		}
		
		result.setResultCode(ResultCode.RESULT_OK);
		result.setResultValue(list);
		
		return gson.toJson(result);
	}
	
	private OInOutRecord buildOInOutRecord(InOutRecord record){
		OInOutRecord oRecord = new OInOutRecord();
		oRecord.setInOutType(record.getDirection());
		oRecord.setDate(record.getTime());
		return oRecord;
	}
	
}
