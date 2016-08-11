<%@ page language="java" import="java.util.*,com.xinyiglass.paging.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="insert.do" method="post">
        <fieldset>
        	<legend>添加员工 NEW</legend>
        	工号：<input name="emp_number" value = ""><br/>
        	姓：<input name="last_name" value = "李"><br/>
        	名称：<input name="first_name" value = "测试"><br/>
        	性别：<input name="sex" value = "F"><br/>
        	邮件：<input name="email" value = ""><br/>
        	电话：<input name="phone_number" value = ""><br/>
        	雇用日期：<input name="hire_date" value = "2011-01-01"><br/>
        	职务ID：<input name="job_id" value = "21"><br/>
        	部门ID：<input name="department_id" value = "21"><br/>
        	薪水：<input name="salary" value = "100"><br/>
        	启用日期：<input name="enable_date" value = "<%=TypeConvert.type2Str(new java.util.Date())%>"><br/>
        	备注：<input name="remark" value = ""><br/>
        	<input type="submit" value="确认">
        	<input  type = "button" value="查看员工"  onclick="window.location.href='/web03/list.do'" />
        </fieldset>
    </form>
  </body>
</html>