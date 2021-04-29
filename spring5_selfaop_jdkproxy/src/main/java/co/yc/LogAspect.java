package co.yc;

import sun.rmi.runtime.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-10 19:21
 */
public class LogAspect implements InvocationHandler {
    private Object target;
    public LogAspect(Object target){this.target=target;}
    public Object creatProxy(){
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),this);

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理对象"+proxy.getClass());
        System.out.println("目标类对象"+method);
        System.out.println("方法中的参数"+args);
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

