package com.kun.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CaoZiye
 * @version 1.0 2018/6/10 20:17
 */
@RestController
@RequestMapping("/local")
public class LocalForwardTestController {

    @RequestMapping("/test")
    public String test() {
        return "local test";
    }

}
