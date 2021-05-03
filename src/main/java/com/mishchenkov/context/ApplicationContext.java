package com.mishchenkov.context;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class<?>, Object> holder;

    public ApplicationContext() {
        holder = new HashMap<>();
    }

    public ApplicationContext(Map<Class<?>, Object> holder) {
        this();
        this.holder.putAll(holder);
    }

    public <T> T getObjectByClass(Class<T> clazz) {
        Object result = holder.get(clazz);
        return (T) result;
    }

    public <T> void putObject(Class<T> key, T value) {
        holder.put(key, value);
    }
}
