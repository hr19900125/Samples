package com.ryan.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.ryan.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	GsonBuilder builder = new GsonBuilder();   
	
	@RequestMapping("auth/login.htm")
	public ModelAndView showLogin(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("auth/login");
	    return mv;
	}
	
	@RequestMapping("auth/403.htm")
	public ModelAndView show403Page(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("auth/403.vm");
		return mv;
	}
	
	@RequestMapping("auth/timeout.htm")
	public ModelAndView showTimeoutPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("auth/timeout");
		return mv;
	}
	
	@RequestMapping("GreenWay.apk")
	public void downloadApk(HttpServletResponse response){
		response.setCharacterEncoding("utf-8");  
        response.setContentType("application/vnd.android.package-archive");  
        response.setHeader("Content-Disposition", "attachment;fileName=GreenWay.apk");  
        InputStream inputStream = null;
        OutputStream os = null;
        try {  
            File file=new File("/home/webapp/download/GreenWay.apk");  
//        	File file=new File("C://log//GreenWay.apk");
            System.out.println(file.getAbsolutePath());  
            inputStream = new FileInputStream(file);  
            os = response.getOutputStream();  
            byte[] b=new byte[1024];  
            int length;  
            while((length=inputStream.read(b))>0){  
                os.write(b,0,length);  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally{
        	if(inputStream != null){
        	   try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
        	
        	if(os != null){
        		try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
	}
	
}
