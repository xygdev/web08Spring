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
function check_username(){
	var xhr=getXhr();
	xhr.open('post','check_username.do',true);
	xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');//post用
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var txt=xhr.responseText;
				$('username_msg').innerHTML=txt;
			}else{
				$('username_msg').innerHTML="验证出错";
			}
		}
	}
	$('username_msg').innerHTML="正在验证...";//模拟用户量大的情况。
	alert("1");
	xhr.send('username='+$F('username'));
	//如果是同步请求，浏览器不会执行以下的代码，而是等待服务器响应回来，在此期间，浏览器会锁定当前页面
	alert("2");//上面的参数决定了这个代码的执行顺序。
}
</script>
</head>
<body>
	<form action="#" method="get">
	  	<legend>注册</legend>
	  		用户名：<input type="text" name="username" id="username" onblur="check_username();"/>
	  		<span class="tips" id="username_msg"></span><br/>
			<input type="submit" value="提交" />
  		</fieldset>
	</form>
</body>
</html>