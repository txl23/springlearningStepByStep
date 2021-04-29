package com.yc.test;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-17 19:07
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;
    @Test
    public  void openAccounttest(){
        Integer accountid=accountService.openAccount(new Accounts(),1);
        System.out.println(accountid);
        Assert.assertNotNull(accountid);
    }
    @Test
    public  void depositetest(){
        Accounts a=new Accounts();
        a.setAccountId(4);
        Accounts aa=accountService.deposite(a,100, OpTypes.deposite.getName(),null);
        System.out.println(aa);
    }
    @Test
    public  void withdrawtest(){
        Accounts a=new Accounts();
        a.setAccountId(4);
        Accounts aa=accountService.withdraw(a,100, OpTypes.deposite.getName(),null);
        System.out.println(aa);
    }
    @Test
    public void transfertest(){
        Accounts out=new Accounts();
        Accounts in=new Accounts();
        out.setAccountId(4);
        in.setAccountId(1);
        this.accountService.transfer(in,out,100);

    }
    @Test
    public  void showBalance(){
        Accounts a =new Accounts();
        a.setAccountId(4);
        a=this.accountService.showBalance(a);
        System.out.println(a);
    }
    @Test
    public  void testfindbyId(){
      Accounts accounts=new Accounts();

      accounts.setAccountId(4);
         List<OpRecord>list=this.accountService.findByid(accounts);
        for (OpRecord a:list){
            System.out.println(a.getId());
            System.out.println(a.getTransferid());
            System.out.println(a.getOptype());
            System.out.println(a.getOptiome());
            System.out.println(a.getOpmoney());
            System.out.println(a.getAccountid());
            System.out.println("________________________________________");
        }
    }

}
