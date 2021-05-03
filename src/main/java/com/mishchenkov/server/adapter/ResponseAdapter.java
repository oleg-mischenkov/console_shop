package com.mishchenkov.server.adapter;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.ProductService;

public abstract class ResponseAdapter {

    protected final ProductService service;

    protected ResponseAdapter(ProductService service) {
        this.service = service;
    }

    /**
     * Method returns some string as special view of the products total count.
     *
     * @return  - product count
     */
    public abstract String getCount();


    /**
     * Method returns some string as special view of the product information.
     *
     * @param itemId    - this is a product identifier
     * @return  - some information about product
     */
    public abstract String getItem(String itemId);

    protected Product getProductBySku(String productSku) {
        return service.selectAll()
                .stream()
                .filter(prod -> prod.getSku().equals(productSku))
                .findFirst().orElseGet(Product::new);
    }
}
