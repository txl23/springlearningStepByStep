package com.yc.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-04 15:40
 */
@Resource
@Component   //被spring容器托管
public class HelloWorld {//创建类对象
    public HelloWorld(){
        System.out.println("无参构造方法");
    }
    public void hello(){
        System.out.println("helloworld");
    }


}
