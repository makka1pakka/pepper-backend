package com.cz.config;

import com.cz.core.EventWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketServerConfig implements WebSocketConfigurer {

    @Autowired
    private EventWebSocketHandler customizeWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 注册自定义customizeWebSocketHandler 使用sockJs
        registry.addHandler(customizeWebSocketHandler, "/sockJs").withSockJS();
        // 注册自定义customizeWebSocketHandler 使用ws
        registry.addHandler(customizeWebSocketHandler, "/ws").setAllowedOrigins("*");
    }
}
