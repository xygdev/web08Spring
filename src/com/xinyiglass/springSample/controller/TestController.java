package com.xinyiglass.springSample.controller;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/test")
public class TestController {
	protected HttpServletRequest req; 
    protected HttpServletResponse res; 
    protected HttpSession sess; 
    
    @ModelAttribute 
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{ 
        this.req = request; 
        this.res = response; 
        this.sess = request.getSession(); 
        req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");  
    } 
	
	//http://192.168.88.123:8080/web08Spring/test/test01.do
	@RequestMapping("/test01.do")
	public String sendMessage(){
		return "testAjax01";
	}
	
	@RequestMapping("/getNumber.do")
    public void getNumber() throws Exception{
        Random r=new Random();
        int number=r.nextInt();
        System.out.println("number:"+number);
        res.getWriter().print(number);
    }
	
	@RequestMapping("/test02.do")
	public String test02(){
		return "testAjax02";
	}
	
	@RequestMapping("/check_username.do")
	public void checkUserName() throws Exception{
		String userName=req.getParameter("username");
		try{//模拟耗时操作
			Thread.sleep(6000);
		}catch (InterruptedException e){ 
			e.printStackTrace();	
		}
		System.out.println("userName:"+userName);
		/*
		if(1==2){//模拟系统异常
			throw new ServletException("some error");
		}*/
		if(userName.equals("samt007")){
			res.getWriter().print("已被占用");
		}else{
			res.getWriter().print("该用户名可以使用");
		}
	}
	
	@RequestMapping("/test03.do")
	public String test03(){
		return "testAjax03-quote";
	}
	
	@RequestMapping("/quote.do")
	public void quote() throws Exception{
		Random r=new Random();
		DecimalFormat df=new DecimalFormat("#.##");
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		for(int i=0;i<10;i++){
			sb.append("{");
			sb.append("\"name\":"+"\"chang"+r.nextInt(30)+"\",");
			sb.append("\"code\":"+"\"6000"+r.nextInt(30)+"\",");
			double price=r.nextInt(100)+r.nextDouble();
			sb.append("\"price\":"+"\""+df.format(price)+"\"");
			sb.append("},");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		res.getWriter().print(sb.toString());
	}
	
}
