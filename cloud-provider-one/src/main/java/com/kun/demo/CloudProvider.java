package com.kun.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author CaoZiye
 * @version 1.0 2018/4/30 15:25
 */
@EnableEurekaClient
@SpringBootApplication
public class CloudProvider {
    
    public static void main(String[] args) {
        SpringApplication.run(CloudProvider.class, args);
    }
    
}
