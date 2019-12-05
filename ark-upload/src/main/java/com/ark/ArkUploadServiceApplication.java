package com.ark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ArkUploadServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArkUploadServiceApplication.class,args);
    }
}
