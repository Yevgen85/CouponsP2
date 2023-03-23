package com.example.couponsp2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfigurations {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
