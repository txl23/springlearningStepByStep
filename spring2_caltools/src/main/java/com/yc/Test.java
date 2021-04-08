package com.yc;


import com.yc.biz.StudentBizImpl;
import com.yc.springframework.context.MyAnnotationConfigApplicationContext;
import com.yc.springframework.context.MyApplicationContext;


/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-05 16:37
 */


public class Test {
    public static void main(String[] args) {
        MyApplicationContext ac = new MyAnnotationConfigApplicationContext(MyAppConfig.class);
        StudentBizImpl hw = (StudentBizImpl) ac.getBean("studentBizImpl");
        hw.add("汤僖龙");
//        HelloWorld hw= (HelloWorld) ac.getBean("hw");
//        hw.show();
    }
}
