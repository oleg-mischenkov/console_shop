package com.mishchenkov.service.impl;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.repository.ProductDAO;
import com.mishchenkov.service.ProductService;
import com.mishchenkov.initialization.StorageInitialization;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO dao;

    public ProductServiceImpl(ProductDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Product> selectAll() {
        return dao.selectAll(StorageInitialization.PRODUCT_TABLE_NAME);
    }

    @Override
    public Product selectElement(Integer key) {
        return dao.selectElement(StorageInitialization.PRODUCT_TABLE_NAME, key);
    }

    @Override
    public void insertElement(Integer key, Product value) {
        dao.insertElement(StorageInitialization.PRODUCT_TABLE_NAME, key, value);
    }

    @Override
    public Map<Integer, Product> selectDataByDiapason(Integer firstKey, Integer secondKey) {
        SortedMap<Integer, Product> map = (SortedMap<Integer, Product>) dao.selectAllDataFromTable(StorageInitialization.PRODUCT_TABLE_NAME);
        return map.subMap(firstKey, secondKey);
    }

    @Override
    public Map<Integer, Product> selectAllData() {
        return dao.selectAllDataFromTable(StorageInitialization.PRODUCT_TABLE_NAME);
    }

    @Override
    public int getGreatestProductKey() {
        return selectAllData().keySet()
                .stream()
                .max(Integer::compareTo)
                .orElseThrow(IllegalStateException::new);
    }
}
