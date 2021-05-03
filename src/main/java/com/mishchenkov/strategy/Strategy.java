package com.mishchenkov.strategy;

public interface Strategy {

    String getString(String strName, String msg);
    int getInt(String strName, String msg);
    boolean getBoolean(String strName, String msg);
    float getFloat(String strName, String msg);
}
