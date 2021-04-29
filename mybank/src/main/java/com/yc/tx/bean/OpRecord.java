package com.yc.tx.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-15 20:57
 */
@Data
public class OpRecord {
    private Integer id;
    private  Integer accountid;
    private  Double opmoney;
    private Timestamp optiome;
    private String optype;
    private String transferid;
}
