package com.mishchenkov.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Item {

    String EMPTY_VALUE = "";

    String title();
    String msg() default EMPTY_VALUE;
}
