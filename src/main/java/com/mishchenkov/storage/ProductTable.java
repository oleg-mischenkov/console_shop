package com.mishchenkov.storage;

import com.mishchenkov.entity.product.Product;

import java.util.Collection;

public class ProductTable extends BaseTable<Integer, Product> {

    public ProductTable() {}

    public ProductTable(Collection<Product> products) {
        int index = 0;

        for (Product element: products) {
            super.insert(index++, element);
        }
    }
}
