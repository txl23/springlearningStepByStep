package com.yc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;



@SpringBootApplication
@EnableConfigurationProperties
public class Springboot1Application {

   public static void main(String[] args) {
       SpringApplication.run(Springboot1Application.class,args);
    }
}
