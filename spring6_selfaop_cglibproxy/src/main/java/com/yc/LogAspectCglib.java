package com.yc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.rmi.runtime.Log;

import java.lang.reflect.Method;
import java.security.PublicKey;
import java.util.Date;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-10 20:48
 */
public class LogAspectCglib implements MethodInterceptor {
    private Object target;
    public LogAspectCglib(Object target){
        this.target=target;
    }
    public Object createProxy(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (method.getName().startsWith("add")){

            Log();
        }
        Object returnValue=method.invoke(this.target,args);
        return returnValue;
    }
    private void Log(){
        System.out.println("=============before advice=========");
        System.out.println("hello,this is"+new Date());
        System.out.println("================");

    }
}
