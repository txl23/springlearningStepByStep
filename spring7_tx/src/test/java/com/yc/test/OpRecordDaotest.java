package com.yc.test;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.AccountsDao;
import com.yc.tx.dao.OpRecordDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-15 21:03
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class OpRecordDaotest {
    @Autowired
    private OpRecordDao accountsdao;

    @Test
    public void testsave(){
        OpRecord OpRecord=new OpRecord();
        OpRecord.setAccountid(1);
        OpRecord.setOpmoney(10.0);
        OpRecord.setOptype(OpTypes.deposite.getName());
        OpRecord.setOptiome(new Timestamp(new Date().getTime()));
        OpRecord.setTransferid("  ");
        accountsdao.saveOpRecord(OpRecord);
    }

    @Test
    public  void testfindall(){
        List<OpRecord>list=accountsdao.findAll();
        Assert.assertNotEquals(0,list.size());
    }
    @Test
    public  void  testByaccountid(){
        List<OpRecord>list=accountsdao.findByAccountid(1);
        Assert.assertNotEquals(0,list.size());
    }

}
