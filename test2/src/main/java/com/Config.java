package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-05 10:06
 */
@Configuration
@ComponentScan(basePackages = {"com.huwei","com.mimi"})
public class Config {
    @Bean
    public Random r(){
        return new Random();
    }
}
