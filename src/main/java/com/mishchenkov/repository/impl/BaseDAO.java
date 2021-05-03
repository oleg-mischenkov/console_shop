package com.mishchenkov.repository.impl;

import com.mishchenkov.repository.DAO;
import com.mishchenkov.storage.DataBase;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseDAO<K,V> implements DAO<K,V> {

    protected DataBase dataBase;

    protected BaseDAO(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public List<V> selectAll(String productName) {
        return dataBase.
                selectAll(productName).stream()
                .map(e -> (V) e)
                .collect(Collectors.toList());
    }

    @Override
    public V selectElement(String tableName, K key) {
        return (V) dataBase.selectElement(tableName, key);
    }

    @Override
    public void insertElement(String tableName, K key, V value) {
        dataBase.insertElement(tableName, key, value);
    }

    @Override
    public Map<K, V> selectAllDataFromTable(String tableName) {
        return (Map<K, V>) dataBase.selectAllDateFromTable(tableName);
    }
}
