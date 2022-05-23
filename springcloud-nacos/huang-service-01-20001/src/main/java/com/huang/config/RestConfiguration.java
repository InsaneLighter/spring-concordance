package com.huang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Time 2022-05-20 13:19
 * Created by Huang
 * className: RestConfiguration
 * Description:
 */
@Configuration
public class RestConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
