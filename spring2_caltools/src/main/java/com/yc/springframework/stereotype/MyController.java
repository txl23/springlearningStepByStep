package com.yc.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-05 16:20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyController {
}
