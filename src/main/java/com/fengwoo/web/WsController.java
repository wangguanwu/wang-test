package com.fengwoo.web;

import com.fengwoo.domain.WangMessage;
import com.fengwoo.domain.WangResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by wgu on 2018/07/09 11:12.
 */
@Controller
public class WsController {
	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public WangResponse say(WangMessage wangMessage)throws Exception{
		Thread.sleep(3000);
		return new WangResponse("Welcome ,"+wangMessage.getName()+"!");
	}

}
