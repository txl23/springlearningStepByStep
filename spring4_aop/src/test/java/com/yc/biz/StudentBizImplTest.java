package com.yc.biz;

import com.yc.MyAppConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyAppConfig.class)
public class StudentBizImplTest {
@Resource(name = "studentBizImpl")
private StudentBiz sb;
@Test
    public void testAdd() {
        sb.add("李四");
    }
    @Test
    public void testUpdate() {
    sb.update("李四");
    }
    @Test
    public void testFind() {
    sb.find("李四");
    }
}