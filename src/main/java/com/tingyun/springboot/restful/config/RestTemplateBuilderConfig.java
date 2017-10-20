package com.tingyun.springboot.restful.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tingyun on 2017/10/20.
 */
@Configuration
public class RestTemplateBuilderConfig {

    @Autowired
    public RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate getRestTemplate() {
        return restTemplateBuilder.build();
    }
}
