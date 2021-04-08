package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.springframework.stereotype.MyComponentScan;
import com.yc.springframework.stereotype.MyConfiguration;
import com.yc.springframework.stereotype.MyBean;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-05 16:37
 */
@MyConfiguration
@MyComponentScan(basePackages = {"com.yc.dao", "com.yc.biz"})
public class MyAppConfig {

//    @MyBean
//    public HelloWorld hw() {      //method.invoke(  MyAppConfig对象 , xxx  )
//        return new HelloWorld();
//    }


}
