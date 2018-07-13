package com.fengwoo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by wang on 2018/07/09 11:00.
 */
@Configuration
@EnableWebSocketMessageBroker//开启使用STOMP协议
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {//注册一个STOMP节点,映射指定URL
		registry.addEndpoint("/endpointWang").withSockJS();// 	注册一个STOMP的endpoint，指定使用SockJS协议
		registry.addEndpoint("/endpointChat").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry){//配置消息代理
//	{		registry.enableSimpleBroker(

//			"/topic");//广播式应配置/topic代理
		registry.enableSimpleBroker("/queue","/topic");

	}
}
