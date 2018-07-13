package com.fengwoo.web;

import com.fengwoo.domain.WangMessage;
import com.fengwoo.domain.WangResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by wgu on 2018/07/09 11:12.
 */
@Controller
public class WsController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@MessageMapping("/welcome")//浏览器向服务端发送请求时，通过这个映射/welcome这个地址，类似@requestMapping
	@SendTo("/topic/getResponse")//服务端有信息时，会对订阅了@SendTo的路径的浏览器发送消息
	public WangResponse say(WangMessage wangMessage)throws Exception{
		Thread.sleep(3000);
		return new WangResponse("Welcome ,"+wangMessage.getName()+"!");
	}
	@MessageMapping("/chat")
	public void handleChat(Principal principal,String msg){
		if(principal.getName().equals("wang")){
			simpMessagingTemplate.convertAndSendToUser("guan","/queue/notifications",principal.getName()+"-send:"+msg);
		}else{
			simpMessagingTemplate.convertAndSendToUser("wang","/queue/notifications",principal.getName()+"-send:"+msg);
		}
	}

}
