<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock Quote-股票报价行情</title>
<script src="//cdn.bootcss.com/prototype/1.7.3/prototype.min.js"></script>
<script type="text/javascript">
var int;
function getXhr(){
	var xhr=null;
	if(window.XMLHttpRequest){
		xhr=new XMLHttpRequest();
	}else{
		xhr=new ActiveXObject('Microsoft.XMLHttp');
	}
	return xhr;
}
function initQuote(){
	//alert("test03 begin");
	//quote();
	int=setInterval(quote,3000);
	//alert("test03 end");
}
function quote(){
	var xhr=getXhr();
	xhr.open('get','quote.do',true);
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4){
			var txt=xhr.responseText;
			var arr=txt.evalJSON();//将json字符串转换成javascript对象组成的数组
			var html='';//将数组中的数据取出来，添加到tbody
			for(i=0;i<arr.length;i++){
				html+='<tr><td>'+arr[i].name+'</td><td>'+arr[i].code+'</td><td>'+arr[i].price+'</td></tr>';
			}
			$('tb1').innerHTML=html;
		}
	}
	xhr.send(null);
}
</script>
</head>
<body onload="initQuote();">
	<div id="d1">
		<div id="d2">股票实时行情</div>
		<div id="d3">
			<table cellpadding="0" cellspacing="0" width="100%">
				<thead><tr><td>股票名称</td><td>股票代码</td><td>股票价格</td></tr></thead>
				<tbody id="tb1"></tbody>
			</table>
			<button onclick="clearInterval(int)">停止刷新</button>
		</div>
	</div>
</body>
</html>