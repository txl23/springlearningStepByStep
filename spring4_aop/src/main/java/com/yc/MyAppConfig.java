package com.yc;

import com.yc.bean.HelloWorld;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-05 16:37
 */
@Configuration
@ComponentScan(basePackages = {"com.yc"})
@EnableAspectJAutoProxy//启用aspect支持
public class MyAppConfig {

//    @MyBean
//    public HelloWorld hw() {      //method.invoke(  MyAppConfig对象 , xxx  )
//        return new HelloWorld();
//    }


}
