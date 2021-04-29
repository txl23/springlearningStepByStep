package com.yc;

import com.yc.biz.StudentBizImpl;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-10 20:48
 */
public class Test {
    public static void main(String[] args) {
        StudentBizImpl sbi=new StudentBizImpl();
        LogAspectCglib lc=new LogAspectCglib(sbi);
        Object obj =lc.createProxy();
        System.out.println(obj);
        if (obj instanceof  StudentBizImpl){
            StudentBizImpl s=(StudentBizImpl)obj;
            s.find("l");
            s.add("a");
            s.update("u");
        }
    }
}
