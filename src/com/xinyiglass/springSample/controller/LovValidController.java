package com.xinyiglass.springSample.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinyiglass.springSample.service.LovValidService;

@Controller
@RequestMapping("/lov")
public class LovValidController {
	
	@Autowired
	LovValidService lVS;
	
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
    
    //JOB值列表值验证
    @RequestMapping(value = "/validJobName.do", method = RequestMethod.POST)
    public void validJobName() throws Exception
    {	
    	String jobname=req.getParameter("jobname");
    	System.out.println(jobname);
    	StringBuffer sb=new StringBuffer();
    	sb=lVS.countJobForPage(jobname);
    	res.getWriter().print(sb);
    }
    
    //通过JOB_NAME获取JOB_ID
    @RequestMapping(value = "/getJobId.do", method = RequestMethod.POST)
    public void getJobId() throws Exception
    {	
    	String jobname=req.getParameter("jobname");
    	System.out.println(jobname);
    	StringBuffer sb=new StringBuffer();
    	sb=lVS.findJobIdForPage(jobname);
    	res.getWriter().print(sb);
    }
    
    //DEPT值列表值验证
    @RequestMapping(value = "/validDeptName.do", method = RequestMethod.POST)
    public void validDeptName() throws Exception
    {	
    	String deptname=req.getParameter("deptname");
    	System.out.println(deptname);
    	StringBuffer sb=new StringBuffer();
    	sb=lVS.countDeptForPage(deptname);
    	res.getWriter().print(sb);
    }
    
    //通过DEPT_NAME获取DEPT_ID
    @RequestMapping(value = "/getDeptId.do", method = RequestMethod.POST)
    public void getDeptId() throws Exception
    {	
    	String deptname=req.getParameter("deptname");
    	System.out.println(deptname);
    	StringBuffer sb=new StringBuffer();
    	sb=lVS.findDeptIdForPage(deptname);
    	res.getWriter().print(sb);
    }

}
