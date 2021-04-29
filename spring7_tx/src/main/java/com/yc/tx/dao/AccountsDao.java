package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;

import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-14 20:27
 */
public interface AccountsDao {
    public int savaAccount(Accounts account);

    public Accounts updateAccount(Accounts account);

    public Accounts findAccount(int accountid);

    public List<Accounts>findAll();

    public void  delete(int accountid);

}
