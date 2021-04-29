package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-27 18:43
 */
@Data
public class AccountVO implements Serializable {

    private static final long serialVersionUID = 6845856180708527688L;

    private  Integer accountid;
    private Double money;
    private Integer inAccountId;
}
