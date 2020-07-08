package com.kun.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding
public class StreamProvider {

    public static void main(String[] args) {
        SpringApplication.run(StreamProvider.class, args);
    }
}
