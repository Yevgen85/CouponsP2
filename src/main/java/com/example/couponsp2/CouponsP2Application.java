package com.example.couponsp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication

public class CouponsP2Application {

    public static void main(String[] args) {
        SpringApplication.run(CouponsP2Application.class, args);



    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}
