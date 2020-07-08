package com.kun.demo.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Example {

    String CHANNEL = "example_out";

    /**
     * 相应的，输入时使用 SubscribableChannel
     */
    @Output(Example.CHANNEL)
    MessageChannel out();
}
