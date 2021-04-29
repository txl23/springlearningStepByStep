package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-27 18:56
 */
@Data
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = -366421879376527749L;
    private Integer code;
    private T data;
    private String msg;
}
