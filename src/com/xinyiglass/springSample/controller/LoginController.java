package com.xinyiglass.springSample.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xinyiglass.springSample.entity.Person;
import com.xinyiglass.springSample.service.PersonService;

@Controller
public class LoginController {
	
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
	
	@RequestMapping("/")
	public String index(){
		return "login";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ModelAndView postLogin(String USER_ID){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listEmpVO");
		mv.addObject("USER_ID",USER_ID);
		sess.setAttribute("USER_ID", Long.parseLong(USER_ID));//设定用户ID到会话
		return mv;
	}

	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String getLogin(){
		return "redirect:/";
	}

	@RequestMapping(value="/logout.do",method=RequestMethod.GET)
	public String getLogout(){
		sess.setAttribute("USER_ID", null);
		return "redirect:/";
	}
	
	@RequestMapping(value="/getPersonData.do")
	public void getPersonData() throws Exception{
		Map<String,Object> personMap = new HashMap<String,Object>();
		PersonService service = new PersonService();
		List<Person> personData = service.getPersonInfo();
		personMap.put("rows",personData);
		//return personMap;
		res.getWriter().print(personData);
	}
	
	@RequestMapping("/person")
	public String toPerson(String name,double age){
		System.out.println(name+" "+age);
		return "hello";
	}
}