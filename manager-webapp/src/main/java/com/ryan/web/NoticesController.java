package com.ryan.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ryan.model.Notice;
import com.ryan.service.NoticeService;
import com.ryan.util.Pagination;
import com.ryan.util.TextUtils;

@Controller
public class NoticesController {

	@Autowired
	NoticeService noticeService;
	
	@RequestMapping("notices.htm")
	public ModelAndView showNotices(@RequestParam(value = "p", defaultValue = "1") int page){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("notices");
		Pagination p = new Pagination();
		p.setPage(page);
		List<Notice> list = noticeService.getNoticeListByPage(p);
		mv.addObject("noticeList", list);
		mv.addObject("p", p);
		return mv;
	}
	
	@RequestMapping("noticeEdit.htm")
	public ModelAndView showNoticeEdit(@RequestParam(value = "nid" , defaultValue="") String nid){
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("noticeEdit");
		mv.addObject("nid", nid);
		
		if(!TextUtils.isEmpty(nid)){
			//编辑
			Notice notice = noticeService.getNotice(Long.valueOf(nid));
			mv.addObject("notice", notice);
			mv.addObject("isEdit", true);
		}else{
			mv.addObject("isEdit", false);
		}
		return mv;
	}
	
}
