<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
   
    <title>My JSP 'ListDemo.jsp' starting page</title>
  </head>
  
  <body>
    <select id='1' data-listurl="list/getSex.do"></select>&nbsp;  
    <select id='2' data-listurl="list/getSex.do"></select>&nbsp;
    <select id='3' data-listurl="list/getSex.do"></select>&nbsp; 
    <select id='4' data-listurl="list/getSex.do"></select>&nbsp;   
    <select id='5' data-listurl="list/getSex.do"></select>&nbsp;    
    <select id='6' data-listurl="list/getSex.do"></select>&nbsp;   
    <select id='7' data-listurl="list/getSex.do"></select>   
    <script type="text/javascript" src="plugin/jQuery/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="plugin/js/jQuery.lov.js"></script>    
  </body>
</html>
