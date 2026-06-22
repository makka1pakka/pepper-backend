package com.cz.core;

import com.cz.entity.Event;
import com.cz.service.ContactService;
import com.cz.service.EventService;
import com.cz.service.PostureService;
import com.cz.vo.PostureVo;
import com.cz.vo.ResultVo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledFuture;

    @Component
    public class EventWebSocketHandler extends TextWebSocketHandler {

        private Logger log = LoggerFactory.getLogger(EventWebSocketHandler.class);
        private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
        private ObjectMapper objectMapper = new ObjectMapper();
        @Autowired
        private EventService eventService;
        @Autowired
        private ContactService contactService;
        @Autowired
        private PostureService postureService;

        private boolean buttonPressed = false;

        @Override
        public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

            System.out.println("客户端ID: " + session.getId() + " 发送消息: " + message.getPayload());
            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    String response = "服务端响应: " + message.getPayload();
                    // 发送消息给客户端
                    s.sendMessage(new TextMessage(response));
                    // 关闭连接
                    // s.close(CloseStatus.NORMAL);
                }
            }

            if ("button_pressed".equals(message.getPayload())) {
                buttonPressed = true;
                PostureVo postureVo = new PostureVo();
                postureVo.setFallDownReply(1);
                Integer latestPostureId = postureService.getLatestPostureId();
                if (latestPostureId != null) {
                    postureService.updatePosture(latestPostureId, postureVo);
                }
            }
        }

        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        public void scheduleCheckButtonPress() {
            final Runnable checker = new Runnable() {
                public void run() {
                    checkButtonPress();
                }
            };
            final ScheduledFuture<?> checkerHandle = scheduler.schedule(checker, 30, TimeUnit.SECONDS);
        }

        public void checkButtonPress() {
            if (!buttonPressed) {
                System.out.println("Button not pressed");
            }
            // Reset the flag
            buttonPressed = false;
        }

        public void sendPostureData(int number) throws Exception {
            String numberStr = String.valueOf(number);
            TextMessage message = new TextMessage(numberStr);
            for (WebSocketSession session : getSessions()) {
                session.sendMessage(message);
            }
            scheduleCheckButtonPress();
        }
        // 其他代码...

        @Scheduled(fixedRate = 10000)  // 每分钟检查一次
        public void checkEventTimes() {
            ResultVo resultVo = eventService.getEvents();
            List<Event> events = (List<Event>) resultVo.getData();

            // 获取当前的日期和时间，并将秒和纳秒字段设置为0
            LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

            // 遍历所有的事件
            for (Event event : events) {
                LocalDateTime startTime = LocalDateTime.ofInstant(event.getStartTime().toInstant(), ZoneId.systemDefault()).withSecond(0).withNano(0);
                LocalDateTime endTime = LocalDateTime.ofInstant(event.getEndTime().toInstant(), ZoneId.systemDefault()).withSecond(0).withNano(0);

                // 如果事件的 startTime 或 endTime 与当前时间精确到分钟重合
                if (now.equals(startTime) || now.equals(endTime)) {
                    for (WebSocketSession session : getSessions()) {
                        try {
                            sendEvent(session, event);
                        } catch (Exception e) {
                            // 在这里添加处理异常的代码
                        }
                    }
                }
            }
        }

        public void sendEvent(WebSocketSession session, Event event) throws Exception {
            String eventJson = objectMapper.writeValueAsString(event);
            session.sendMessage(new TextMessage(eventJson));
        }

        public List<WebSocketSession> getSessions() {
            return sessions;
        }

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            log.info("与客户端建立连接...");
            //连接成功时调用该方法
            System.out.println("WebSocket connected: " + session.getId());
            sessions.add(session);
        }

        @Override
        public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
            //发生错误时调用该方法
            System.err.println("WebSocket error: " + exception.getMessage());
            session.close(CloseStatus.SERVER_ERROR);
            log.error("连接异常", exception);
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
            //连接关闭时调用该方法
            System.out.println("WebSocket closed: " + session.getId());
            sessions.remove(session);
            super.afterConnectionClosed(session, closeStatus);
            log.info("与客户端断开连接...");
        }

        @Override
        public boolean supportsPartialMessages() {
            return false;
        }
    }