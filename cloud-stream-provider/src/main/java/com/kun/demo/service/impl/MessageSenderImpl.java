package com.kun.demo.service.impl;

import com.kun.demo.service.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@Slf4j
@EnableBinding(Source.class)
public class MessageSenderImpl implements MessageSender {

    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String uuid = UUID.randomUUID().toString();
        log.info("UUID: {}", uuid);
        output.send(MessageBuilder.withPayload(uuid).build());
        return uuid;
    }
}
