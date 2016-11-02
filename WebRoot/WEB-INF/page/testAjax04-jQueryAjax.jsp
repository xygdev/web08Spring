<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax和jQuery</title>
<base href="<%=basePath%>"> 
<script src="plugin/jQuery/jQuery-2.1.4.min.js"></script>
<style type="text/css">
table{margin-left:400px; margin-top:100px; font-size:20px;}
.selected{background-color:#fff8cd;}
.item{cursor:pointer}
</style>
<script type="text/javascript">
//百度联想：http://www.oschina.net/code/snippet_1258911_38692
$(function(){
	$('#key').keyup(function(){
		$.ajax({
			'url':'test/find.do',
			'data':'key='+$('#key').val(),
			//'url':'http://suggestion.baidu.com/su',
			//'data':'json=1&cb=queryList&wd='+$('#key').val(),
			'type':'post',
			'dataType':'text',
			'success':function(data){
				//alert("data:"+data.q);
				var arr=data.split(',');
				$('#tips').empty();
				for(i=0;i<arr.length;i++){
					$('#tips').append("<div class='item' style='border:1px solid silver'>"+arr[i]+"</div>");
				}
				//鼠标经过的时候加亮
				$('.item').mouseenter(function(){
					$(this).addClass('selected').siblings().removeClass('selected');
				});
				//当光标点击某个选项时，将该选项的值复制到key
				$('.item').click(function(){
					$('#key').val($(this).text());
					$('#tips').empty();
				});
			},
			'error':function(){
				alert("error!");
			}
		});
	});
});
</script>
</head>
<body>
<table cellpadding="0" cellspacing="0">
	<tr>
		<td><input name="key" id="key"></td>
		<td><input type="button" value="Search"></td>
	</tr>
	<tr>
		<td colspan="2">
			<div id="tips"></div>
		</td>
	</tr>
</table>
</body>
</html>