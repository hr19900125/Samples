package com.ryan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("home.htm")
    public ModelAndView showHomePage(){
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }
	
	@RequestMapping("top.htm")
	public ModelAndView showTop(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("top");
		return mv;
	}
	
	@RequestMapping("menu.htm")
    public ModelAndView showMenu(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("menu");
		return mv;
    }
	
	@RequestMapping("main.htm")
	public ModelAndView showMain(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("archives.htm")
	public ModelAndView showArchives(){
		ModelAndView mv =new ModelAndView();
		mv.setViewName("archives");
		return mv;
	}
}
