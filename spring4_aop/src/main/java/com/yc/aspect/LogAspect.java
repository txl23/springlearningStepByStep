package com.yc.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-09 20:12
 */
@Aspect
@Component
public class LogAspect {
    //切入点声明  point signature
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))")//切入点表达式：哪些方法上增强

    private void add(){

    }
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))")
    private void update(){

    }
    @Pointcut("add()||update()")
    private  void addAndUpdate(){

    }
//    切入点表达式的语法：？代表出现0次或一次
//    modifiers—pattern：修饰衔
//    ret-type-pattern：返回类型

    @Before("com.yc.aspect.LogAspect.add()")
    public  void log(){
        System.out.println("===================前置增强的日志======================");
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr=sdf.format(d);
        System.out.println("执行时间为："+dstr);
        System.out.println("==================前置增强的日志结束=====================");
    }
}
