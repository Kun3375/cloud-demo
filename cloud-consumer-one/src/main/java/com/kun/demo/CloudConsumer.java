package com.kun.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author CaoZiye
 * @version 1.0 2018/5/1 9:10
 */
@EnableFeignClients(basePackages = "com.kun.demo.service.feign")
@SpringCloudApplication
public class CloudConsumer {
    
    public static void main(String[] args) {
        SpringApplication.run(CloudConsumer.class, args);
    }
    
}
