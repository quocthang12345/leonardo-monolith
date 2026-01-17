package com.leonardo.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.leonardo.entity.ChatMessage;

import java.util.Objects;

@Controller
public class WebSocketAPI {
		
 	@MessageMapping(value = "/sendMessage")
    @SendTo(value = "/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chat) {
        return chat;
    }

 	@MessageMapping(value = "/addUser")
    @SendTo(value = "/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chat, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chat.getSender());
        return chat;
    }
}
