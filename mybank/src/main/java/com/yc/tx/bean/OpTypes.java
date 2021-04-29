package com.yc.tx.bean;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-17 14:18
 */
public enum OpTypes {
    deposite("deposite",1),withdraw("withdraw",2),transder("transfer",3);
    private String name;
    private int index;

    private  OpTypes(String name,int index){
        this.name=name;
        this.index=index;
    }
//覆盖方法

    @Override
    public String toString() {
        return this.index+"_"+this.name;
    }


    public String getName(){
        return this.name;
    }
}
