package com.yc.test;

import com.yc.tx.bean.Accounts;
import com.yc.tx.AppConfig;
import com.yc.tx.dao.AccountsDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-15 19:06
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class testDao {
    @Autowired
    private DataSource dataSource;
    @Test
    public  void testDatasource() throws SQLException {
        Assert.assertNotNull(dataSource);
        System.out.println(dataSource.getConnection());
    }

   @Autowired
   private AccountsDao accountsDao;
    @Test
    public void testAccountsDaoImpl(){
        Assert.assertNotNull(accountsDao);
    }
    @Test
    public void testsaveAccount(){
        Accounts a=new Accounts();
        a.setBalance(10.0);
        int accountid=accountsDao.savaAccount(a);
        System.out.println("新开户头为"+accountid);
    }
    @Test
    public void testfindAll(){
        List<Accounts>list=this.accountsDao.findAll();
        for (Accounts a:list){
            System.out.println("户头"+a.getAccountId());
            System.out.println("余额"+a.getBalance());
        }
    }
    @Test
    public void testfindaccount(){
        Accounts a=this.accountsDao.findAccount(2);

            System.out.println("户头"+a.getAccountId());
            System.out.println("余额"+a.getBalance());

    }
}
