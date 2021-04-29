package co.yc.biz;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-10 20:10
 */
public class StudentBizImpl implements StudentBiz{
    @Override
    public int add(String name) {
        System.out.println("add"+name);
        return 100;
    }

    @Override
    public void update(String name) {
        System.out.println("update"+name);


    }

    @Override
    public String find(String name) {
        System.out.println("find"+name);
        return  name+"===";
    }
}
