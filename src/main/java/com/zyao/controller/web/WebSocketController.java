package com.zyao.controller.web;

import com.zyao.modal.socket.Greeting;
import com.zyao.modal.socket.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        // 处理客户端发送的消息，并广播到所有订阅了“/topic/greetings”的客户端
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
