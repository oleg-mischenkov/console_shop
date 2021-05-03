package com.mishchenkov.storage;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

public class DataBase {

    private final TreeMap<String, BaseTable<Object, Object>> storage = new TreeMap<>();

    public String[] showTables() {
        return storage.keySet().toArray(new String[0]);
    }

    public BaseTable setTable(String tableName, BaseTable table) {
        return storage.put(tableName, table);
    }

    public void insertElement(String tableName, Object key, Object value) {
        BaseTable table = storage.get(tableName);
        table.insert(key, value);
    }

    public boolean deleteElement(String tableName, Object key) {
        BaseTable table = storage.get(tableName);
        return table.remove(key);
    }

    public Object selectElement(String tableName, Object key) {
        BaseTable table = storage.get(tableName);
        return table.select(key);
    }

    public List<Object> selectAll(String tableName) {
        BaseTable table = storage.get(tableName);
        return table.selectAll();
    }

    public Object selectElementByFeature(String tableName, Predicate<Object> predicate) {
        return storage.get(tableName)
                .selectAll()
                .stream()
                .filter(predicate)
                .findFirst().orElse(null);
    }

    public Map<Object, Object> selectAllDateFromTable(String tableName) {
        return storage.get(tableName).selectAllDate();
    }
}
