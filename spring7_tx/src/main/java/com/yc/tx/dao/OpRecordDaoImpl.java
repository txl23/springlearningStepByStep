package com.yc.tx.dao;

import com.yc.tx.bean.OpRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-17 14:29
 */
@Repository
public class OpRecordDaoImpl implements OpRecordDao{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }
    @Override
    public void saveOpRecord(OpRecord opRecord) {
        String sql=" insert into oprecord(accountid,opmoney,optime,optype,transferid)values(?,?,?,?,? ) ";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setInt(1,opRecord.getAccountid());
            pstmt.setDouble(2,opRecord.getOpmoney());
            pstmt.setTimestamp(3,opRecord.getOptiome());
            pstmt.setString(4,opRecord.getOptype());
            pstmt.setString(5,opRecord.getTransferid());
            return pstmt;
        });
    }

    @Override
    public List<OpRecord> findAll() {
        String  sql="select * from oprecord";
        List<OpRecord>list=this.jdbcTemplate.query(sql,(ResultSet,rowNum)->{
            OpRecord a =new OpRecord();
            a.setId(ResultSet.getInt("id"));
            a.setAccountid(ResultSet.getInt("accountid"));
            a.setOpmoney(ResultSet.getDouble("opmoney"));
            a.setOptiome(ResultSet.getTimestamp("optime"));
            a.setOptype(ResultSet.getString("optype"));
            a.setTransferid(ResultSet.getString("transferid"));
            return  a;
        });
        return list;
    }

    @Override
    public List<OpRecord> findByAccountid(int accountid) {
        String sql="select *from oprecord where accountid=?";
        List<OpRecord>list=this.jdbcTemplate.query(sql,(ResultSet,rowNum)->{
            OpRecord a=new OpRecord();
            a.setId(ResultSet.getInt("id"));
            a.setAccountid(ResultSet.getInt("accountid"));
            a.setOpmoney(ResultSet.getDouble("opmoney"));
            a.setOptiome(ResultSet.getTimestamp("optime"));
            a.setOptype(ResultSet.getString("optype"));
            a.setTransferid(ResultSet.getString("transferid"));
            return  a;
        },accountid);
        return list;
    }
}
