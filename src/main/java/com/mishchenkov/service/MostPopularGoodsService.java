package com.mishchenkov.service;

import com.mishchenkov.entity.product.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class MostPopularGoodsService {

    private final Map<Integer, Product> storage;

    public MostPopularGoodsService(int storageSize) {

        storage = new LinkedHashMap<Integer,Product>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer,Product> eldest) {
                return size() > storageSize;
            }
        };
    }

    public Product[] getAllProducts() {
        return storage.values().toArray(new Product[0]);
    }

    public void putProduct(Integer key, Product value) {
        storage.put(key, value);
    }

    public int getCount() {
        return storage.size();
    }
}
