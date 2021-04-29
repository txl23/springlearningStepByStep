package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-15 19:33
 */
@Repository
public class AccountsDaoImpl implements AccountsDao{
    private JdbcTemplate jdbcTemplate;
    @Override
    public int savaAccount(final Accounts account) {
         String sql="insert into accounts(balance) values(?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "accountid" });
           ps.setDouble(1,account.getBalance());
            return ps;
        }, keyHolder);
    return ((Long)keyHolder.getKey()).intValue();
    }

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public Accounts updateAccount(Accounts account) {
       String sql="update accounts set balance=? where accountid=?";
       this.jdbcTemplate.update(sql,account.getBalance(),account.getAccountId());
       return account;
    }

    @Override
    public Accounts findAccount(int accountid) {
        String sql="select *from accounts where accountid=? ";
        Accounts accounts = jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> {
                    Accounts a = new Accounts();
                    a.setAccountId(resultSet.getInt("accountid"));
                    a.setBalance(resultSet.getDouble("balance"));
                    return a;
                },
                accountid);
        return accounts;
    }

    @Override
    public List<Accounts> findAll() {
        String sql="select *from accounts";
        List<Accounts> list = this.jdbcTemplate.query(
               sql,
                (resultSet, rowNum) -> {
                    Accounts a = new Accounts();
                    a.setAccountId((resultSet.getInt("accountid")));
                    a.setBalance(resultSet.getDouble("balance"));
                    return a;
                });
        return list;
    }

    @Override
    public void delete(int accountid) {
        String sql="delete from accounts where accountid=?";
        this.jdbcTemplate.update(sql,accountid);

    }
}
