package com.yc.biz;

import javax.naming.Name;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-10 20:48
 */
public class StudentBizImpl {
    public int add(String Name){
        System.out.println("add++++"+ Name);
        return 100;
    }

    public void update(String name) {
        System.out.println("update"+name);


    }
    public String find(String name) {
        System.out.println("find"+name);
        return  name+"===";
    }
}
