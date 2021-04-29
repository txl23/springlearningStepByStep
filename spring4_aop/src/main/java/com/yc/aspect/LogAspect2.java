package com.yc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-10 18:51
 */
@Aspect
@Component
@Order(value = 0)
public class LogAspect2 {
    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    public Object computer(ProceedingJoinPoint pjp)throws  Throwable{
        System.out.println("=================compute2===========");
        long start=System.currentTimeMillis();
        Object retVal=pjp.proceed();
        long end =System.currentTimeMillis();
        System.out.println("================copute2退出了=============");
        System.out.println("****************这个方法的运行时长为"+(end-start));
        return retVal;
    }
}

