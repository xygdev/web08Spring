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

import com.xinyiglass.springSample.entity.EmpVO;
import com.xinyiglass.springSample.service.EmpVOService;

@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	EmpVOService empVOService;
	
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
    
	@RequestMapping("/listEmpVO.do")
	public String listEmpVO(){
		return "listEmpVO";
	}

	@RequestMapping(value = "/getEmpPage.do", method = RequestMethod.POST)
	public void getEmpPage() throws Exception
	{   	
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int pageNo=Integer.parseInt(req.getParameter("pageNo"));
		boolean goLastPage=Boolean.parseBoolean(req.getParameter("goLastPage"));
		String orderby=req.getParameter("orderby");
		res.getWriter().print(empVOService.findForPage(pageSize, pageNo, goLastPage, orderby));
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public void delete() throws Exception
	{   	
		Long empId = Long.parseLong(req.getParameter("EMP_ID"));
		res.getWriter().print(empVOService.delete(empId).toJsonStr());
	}

	@RequestMapping(value = "/preUpdate.do", method = RequestMethod.POST)
	public void preUpdate() throws Exception
	{   	
		Long empId = Long.parseLong(req.getParameter("EMP_ID"));
		EmpVO empVO = empVOService.findById(empId);
		sess.setAttribute("lockEmpVO", empVO);//记录在session变量
		//所有返回给页面的json数据的栏位都以数据库栏位为主！
		res.getWriter().print(empVOService.findByIdForJson(empId));
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public void update() throws Exception
	{   	
		Long empId = Long.parseLong(req.getParameter("EMP_ID"));
		EmpVO lockEmpVO = (EmpVO)sess.getAttribute("lockEmpVO");
		if (lockEmpVO ==null){
			throw new RuntimeException("更新数据出错:会话数据已经无效!请返回再重新操作!");
		}
		if (!empId.equals(lockEmpVO.getEmpId())){
			throw new RuntimeException("更新的数据无法匹配!请重新查询!");
		}
		EmpVO e = new EmpVO();//复制对象！
		e = (EmpVO) lockEmpVO.clone();
		e.setEmpNumber(req.getParameter("EMP_NUMBER"));
		e.setEmpId(empId);
		e.setFullName(e.getLastName()+","+e.getFirstName());
		e.setSex(req.getParameter("SEX"));
		e.setPhoneNumber(req.getParameter("PHONE_NUMBER"));
		e.setHireDate(TypeConvert.str2sDate(req.getParameter("HIRE_DATE")));
		e.setSalary(TypeConvert.str2Double(req.getParameter("SALARY")));
		e.setJobId(TypeConvert.str2Long(req.getParameter("JOB_ID")));
		e.setDepartmentId(TypeConvert.str2Long(req.getParameter("DEPARTMENT_ID")));
		e.setRemark(req.getParameter("REMARK"));
		res.getWriter().print(empVOService.update(lockEmpVO, e).toJsonStr());
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public void insret() throws Exception
	{   	
		EmpVO e = new EmpVO();
		e.setEmpNumber(req.getParameter("EMP_NUMBER"));
		e.setFirstName(req.getParameter("FIRST_NAME"));
		e.setLastName(req.getParameter("LAST_NAME"));
		e.setFullName(e.getLastName()+","+e.getFirstName());
		e.setSex(req.getParameter("SEX"));
		e.setEmail(req.getParameter("EMAIL"));
		e.setPhoneNumber(req.getParameter("PHONE_NUMBER"));
		e.setHireDate(TypeConvert.str2sDate(req.getParameter("HIRE_DATE")));
		e.setEnableDate(TypeConvert.str2Timestamp(req.getParameter("ENABLE_DATE")));
		e.setJobId(TypeConvert.str2Long(req.getParameter("JOB_ID")));
		e.setSalary(TypeConvert.str2Double(req.getParameter("SALARY")));
		e.setManagerId(TypeConvert.str2Long(req.getParameter("MANAGER_ID")));
		e.setDepartmentId(TypeConvert.str2Long(req.getParameter("DEPARTMENT_ID")));
		e.setUserId(TypeConvert.str2Long(req.getParameter("USER_ID")));
		e.setDisabledDate(null);
		e.setRemark(req.getParameter("REMARK"));
		res.getWriter().print(empVOService.insert(e).toJsonStr());
	}
}
