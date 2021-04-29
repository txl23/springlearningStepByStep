package com.yc.bean;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.swing.*;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-05 16:33
 */
@Component
public class HelloWorld {
    @PostConstruct
    public void setup(){
        System.out.println("MyPostConstruct");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("MyPreDestroy");
    }
    public HelloWorld(){
        System.out.println("helloworld 构造");
    }
    public void show(){
        System.out.println("show");
    }

}
