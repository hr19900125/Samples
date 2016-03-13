package com.ryan.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ryan.model.TestBean;
import com.ryan.service.TestService;

@Controller
public class HelloWorldController {

	@Autowired
	private TestService testService;

	@RequestMapping ( "/hello.htm" )  
	public ModelAndView hello() throws Exception {
        // 1、收集参数、验证参数
        // 2、绑定参数到命令对象
        // 3、将命令对象传入业务对象进行业务处理
        // 4、选择下一个页面
        ModelAndView mv = new ModelAndView();
        // 添加模型数据 可以是任意的POJO对象
        mv.addObject("message", "Hello World!");
        // 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        mv.setViewName("hello");
        TestBean result = testService.getTestInfo();
        System.out.println(result.getId()+"  "+result.getUserName());
        return mv;
    }
}  
