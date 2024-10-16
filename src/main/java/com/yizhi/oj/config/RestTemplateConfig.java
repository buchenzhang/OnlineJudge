package com.yizhi.oj.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Zing
 * @Date: 2023/04/09/13:30
 * @Description:
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestTemplateConfig {


    private final RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }


}
