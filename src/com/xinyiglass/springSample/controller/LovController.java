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

import xygdev.commons.util.TypeConvert;

import com.xinyiglass.springSample.service.LovService;

@Controller
@RequestMapping("/lov")
public class LovController {
	
	@Autowired
	LovService lovService;
	
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

	//下面是2个值列表的获取，一个是工作Job，一个是部门dept
    //http://localhost:8080/web08Spring/lov/getJobPage.do
	@RequestMapping(value = "/getJobPage.do", method = RequestMethod.POST)
	public void getJobPage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
		Long jobId=TypeConvert.str2Long(req.getParameter("JOB_ID"));
		String jobName=req.getParameter("JOB_NAME");//注意，测试的时候，请求URL地址不可以直接出现百分号等特殊字符。
		System.out.println("jobName1:"+req.getParameter("JOB_NAME"));
		res.getWriter().print(lovService.findJobForPage(pageSize, pageNo, goLastPage, jobId, jobName));
	}
	
	@RequestMapping(value = "/getDeptPage.do", method = RequestMethod.POST)
	public void getDeptPage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
		String deptName=req.getParameter("DEPT_NAME");
		String deptTypeDesc=req.getParameter("DEPT_TYPE_DESC");
		String managerName=req.getParameter("MANAGER_NAME");
		res.getWriter().print(lovService.findDeptForPage(pageSize, pageNo, goLastPage, deptName, deptTypeDesc, managerName));
	}
	
    //JOB值列表值验证
    @RequestMapping(value = "/validJobName.do", method = RequestMethod.GET)
    public void validJobName() throws Exception
    {	
    	String jobName=req.getParameter("jobname");
    	System.out.println(jobName);
    	res.getWriter().print(lovService.countByJobName(jobName));
    }
    
    //通过JOB_NAME获取JOB_ID
    //http://localhost:8080/web08Spring/lov/getJobId.do?jobname=123
    @RequestMapping(value = "/getJobId.do", method = RequestMethod.GET)
    public void getJobId() throws Exception
    {	
    	String jobName=req.getParameter("jobname");
    	System.out.println(jobName);
    	res.getWriter().print(lovService.findForJobId(jobName));
    }
    
    //DEPT值列表值验证
    @RequestMapping(value = "/validDeptName.do", method = RequestMethod.POST)
    public void validDeptName() throws Exception
    {	
    	String deptName=req.getParameter("deptname");
    	System.out.println(deptName);
    	res.getWriter().print(lovService.countByDeptName(deptName));
    }
    
    //通过DEPT_NAME获取DEPT_ID
    @RequestMapping(value = "/getDeptId.do", method = RequestMethod.POST)
    public void getDeptId() throws Exception
    {	
    	String deptName=req.getParameter("deptname");
    	System.out.println(deptName);
    	res.getWriter().print(lovService.findForDeptId(deptName));
    }
	
}
