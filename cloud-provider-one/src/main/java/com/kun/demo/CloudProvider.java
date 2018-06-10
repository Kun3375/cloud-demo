package com.kun.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author CaoZiye
 * @version 1.0 2018/4/30 15:25
 */
@SpringCloudApplication
public class CloudProvider {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider.class, args);
    }

}