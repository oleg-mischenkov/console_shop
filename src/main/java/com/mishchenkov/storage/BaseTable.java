package com.mishchenkov.storage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class BaseTable<K, V> {

    protected TreeMap<K, V> table = new TreeMap<>();

    public void insert(K key, V value) {
        table.put(key, value);
    }

    public boolean remove(K key) {
        return table.remove(key) != null;
    }

    public V select(K key) {
        return table.get(key);
    }

    public List<V> selectAll() {
        return new ArrayList<>( table.values() );
    }

    public Map<K,V> selectAllDate() {
        return table;
    }

    public List<V> selectAll(Comparator<V> comparator) {
        return table.values()
                .stream()
                .sorted(comparator)
                .collect( Collectors.toList() );
    }

    @Override
    public String toString() {
        return "BaseTable{" +
                "table=" + table +
                '}';
    }
}
