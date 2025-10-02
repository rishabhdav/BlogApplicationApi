package com.Blog.springmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public  Interceptor interceptor(){
        return new Interceptor();
    }

    
    
}
