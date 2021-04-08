package com.yc.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-05 11:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyComponentScan {
    String[]basePackages()default {};
}
