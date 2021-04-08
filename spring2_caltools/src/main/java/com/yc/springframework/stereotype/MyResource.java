package com.yc.springframework.stereotype;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 16:22
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface MyResource {
    String name() default "";;
}
