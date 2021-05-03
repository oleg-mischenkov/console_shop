package com.mishchenkov.service;

import java.util.List;
import java.util.Map;

public interface Service<K,V> {

    List<V> selectAll();

    V selectElement(K key);

    void insertElement(K key, V value);

    Map<K,V> selectDataByDiapason(K firstKey, K secondKey);

    Map<K,V> selectAllData();
}
