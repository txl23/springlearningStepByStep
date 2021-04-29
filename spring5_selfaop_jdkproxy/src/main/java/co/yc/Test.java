package co.yc;

import co.yc.biz.StudentBiz;
import co.yc.biz.StudentBizImpl;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-10 20:19
 */
public class Test {
    public static void main(String[] args) {
        StudentBiz target=new StudentBizImpl();
        LogAspect la=new LogAspect(target);
        Object obj=la.creatProxy();
        if(obj instanceof  StudentBiz){
            StudentBiz sb=(StudentBiz)obj;
            sb.find("z");
            sb.add("l");
            sb.update("w");
        }
    }
}
