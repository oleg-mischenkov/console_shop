package com.mishchenkov.service;

import com.mishchenkov.entity.product.Product;

public interface ProductService extends Service<Integer, Product> {

    int getGreatestProductKey();

}
