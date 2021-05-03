package com.mishchenkov.repository;

import java.util.List;
import java.util.Map;

public interface DAO<K,V> {

    List<V> selectAll(String tableName);

    V selectElement(String tableName, K key);

    Map<K,V> selectAllDataFromTable(String tableName);

    void insertElement(String tableName, K key, V value);
}
