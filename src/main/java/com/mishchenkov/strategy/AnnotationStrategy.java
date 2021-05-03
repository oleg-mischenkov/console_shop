package com.mishchenkov.strategy;

public interface AnnotationStrategy {

    Object getObject(Class<?> typeClass, String name, String msg);

}
