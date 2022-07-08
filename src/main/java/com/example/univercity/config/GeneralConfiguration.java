package com.example.univercity.config;

import com.example.univercity.model.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {

    @Bean
    public Result result(){
        return new Result();
    }
}
