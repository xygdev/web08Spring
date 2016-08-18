package com.xinyiglass.springSample.websocket;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SystemWebSocketHandler extends  TextWebSocketHandler {
	 
 
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();;
 
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	System.out.println("ConnectionEstablished");
        users.add(session);
        System.out.println("当前用户"+users.size()+",session:"+session.toString());
    }
    /**
     * 在UI在用js调用websocket.send()时候，会调用该方法
     *
     */
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		sendMessageToUsers(session,message);
	}
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
        if(session.isOpen()){
        	session.close();
        }
        users.remove(session);
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        users.remove(session);
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    /**
     * 给所有在线用户发送消息
     *
     */
    public void sendMessageToUsers(WebSocketSession session,TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                	user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}