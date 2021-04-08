package com.yc.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 16:18
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAutowired {
}
