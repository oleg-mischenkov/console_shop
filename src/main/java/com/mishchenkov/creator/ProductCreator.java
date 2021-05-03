package com.mishchenkov.creator;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.strategy.Strategy;
import com.mishchenkov.constant.Constants;


public class ProductCreator implements Creator {

    protected final Strategy strategy;

    public ProductCreator(Strategy strategy) {
        this.strategy = strategy;
    }

    protected void setBasicField(Product product) {
        product.setName(strategy.getString("Name", Constants.PROD_NAME_MSG));
        product.setSku(strategy.getString("Sku", Constants.PROD_SKU_MSG));
        product.setDescription(strategy.getString("Description", Constants.PROD_DESCRIPTION_MSG));
        product.setState(strategy.getBoolean("State", Constants.PROD_STATE_MSG));
        product.setPrice(strategy.getFloat("Price", Constants.PROD_PRICE_MSG));
    }

    @Override
    public Product createProduct() {
        Product product = new Product();
        setBasicField(product);

        return product;
    }
}
