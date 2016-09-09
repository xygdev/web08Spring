
document.write("<script language=javascript src='plugin/jQuery/jQuery-2.1.4.min.js'></script>");
document.write("<script language=javascript src='plugin/layer/layer.js'></script>");
document.write("<script language=javascript src='plugin/js/sockjs-0.3.min.js'></script>");
	var ws = null;
	//获取服务器的地址。例如：ws://192.168.88.123:8080/web08Spring/
	/*path=window.document.location.pathname;
	project=path.substring(0,path.substr(1).indexOf('/')+1);
	href=window.document.location.href;
	host=(href.substring(0,href.indexOf(path))).replace(/^http:/, 'ws:');
	var basePath = host+project+'/';*/
	var basePath=$('base').attr('href').replace(/^http:/, 'ws:');
	console.log("basePath:"+basePath);
	//var basePath = "ws://192.168.88.123:8080/web08Spring/";
	if ('WebSocket' in window) {
    	 ws = new WebSocket(basePath+'webSocketServer'); 
	} 
	else if ('MozWebSocket' in window) {
		ws = new MozWebSocket(basePath+"webSocketServer");
	} 
	else {
		ws = new SockJS(basePath+"sockjs/webSocketServer");
	}
    ws.onopen = function () {
        
    };
    ws.onmessage = function (event) {
    	pop(event.data);
    };
    ws.onclose = function (event) {
    	 ws.close();
    };
//提示信息
function pop(message){
	layer.alert(message);
}
