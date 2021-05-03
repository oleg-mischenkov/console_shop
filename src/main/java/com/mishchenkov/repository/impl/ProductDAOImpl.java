package com.mishchenkov.repository.impl;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.repository.ProductDAO;
import com.mishchenkov.storage.DataBase;

public class ProductDAOImpl extends BaseDAO<Integer, Product> implements ProductDAO {

    public ProductDAOImpl(DataBase dataBase) {
        super(dataBase);
    }

}
