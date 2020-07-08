package com.kun.demo.controller;

import com.kun.demo.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/stream")
@RestController
public class SourceController {

    @Autowired
    private MessageSender messageSender;

    @RequestMapping("/send")
    public String send() {
        return messageSender.send();
    }
}
