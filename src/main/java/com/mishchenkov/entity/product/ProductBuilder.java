package com.mishchenkov.entity.product;

public class ProductBuilder {

    private Product product;

    public ProductBuilder() {
        product = new Product();
    }

    public ProductBuilder setName(String name) {
        product.setName(name);
        return this;
    }

    public ProductBuilder setSku(String sku) {
        product.setSku(sku);
        return this;
    }

    public ProductBuilder setDescription(String description) {
        product.setDescription(description);
        return this;
    }

    public ProductBuilder setState(boolean state) {
        product.setState(state);
        return this;
    }

    public ProductBuilder setPrice(int price) {
        product.setPrice(price);
        return this;
    }

    public ProductBuilder setPrice(float price) {
        product.setPrice(price);
        return this;
    }

    public Product build() {
        return product;
    }

    public ProductBuilder clear() {
        product = new Product();
        return this;
    }
}
