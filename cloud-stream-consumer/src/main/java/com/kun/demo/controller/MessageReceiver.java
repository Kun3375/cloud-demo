package com.kun.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@Slf4j
@EnableBinding(Sink.class)
public class MessageReceiver {

    @Value("${server.port}")
    private Integer port;

    @StreamListener(Sink.INPUT)
    public void receive(Message<String> message) {
        String msg = message.getPayload();
        log.info("{} receive: {}", port, msg);
    }
}
