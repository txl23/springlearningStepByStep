package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
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
        a.setAccountId(9);
        Accounts aa=accountService.deposite(a,100, OpTypes.deposite.getName(),null);
        System.out.println(aa);
    }
    @Test
    public  void withdrawtest(){
        Accounts a=new Accounts();
        a.setAccountId(9);
        Accounts aa=accountService.withdraw(a,100, OpTypes.deposite.getName(),null);
        System.out.println(aa);
    }
    @Test
    public void transfertest(){
        Accounts out=new Accounts();
        Accounts in=new Accounts();
        out.setAccountId(9);
        in.setAccountId(1);
        this.accountService.transfer(in,out,1);

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

        accounts.setAccountId(9);
        List<OpRecord> list=this.accountService.findByid(accounts);
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