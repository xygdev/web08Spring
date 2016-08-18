document.write("<script language=javascript src='http://192.168.88.123:8080/web08Spring/plugin/jQuery/jQuery-2.1.4.min.js'></script>");
document.write("<script language=javascript src='http://192.168.88.123:8080/web08Spring/plugin/layer/layer.js'></script>");
document.write("<script language=javascript src='http://192.168.88.123:8080/web08Spring/plugin/js/sockjs-0.3.min.js'></script>");
	var ws = null;
	var basePath = "ws://192.168.88.123:8080/web08Spring/";
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
