<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://192.168.88.123:8080/web08Spring/plugin/js/webSocket.js"></script>
<script type="text/javascript">
function getXhr(){
	var xhr=null;
	if(window.XMLHttpRequest){
		xhr=new XMLHttpRequest();
	}else{
		xhr=new ActiveXObject('Microsoft.XMLHttp');
	}
	return xhr;
}
function $(id){//依据id返回dom节点
	return document.getElementById(id);
}
function $F(id){//返回id对应的值
	return $(id).value;
}
function getNumber(){
	var xhr=getXhr();
	xhr.open('get','getNumber.do?'+Math.random(),true);
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4){
			var txt=xhr.responseText;
			$('d1').innerHTML=txt;
		}
	};
	xhr.send(null);
}
</script>
</head>
<body>
	<a href="javascript:;" onclick="getNumber();">点这儿，在链接低下显示随机数</a>
	<div id="d1"></div>
</body>
</html>