package com.mishchenkov.server.adapter;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.ProductService;

public class TCPResponseAdapter extends ResponseAdapter {

    public TCPResponseAdapter(ProductService service) {
        super(service);
    }

    @Override
    public String getCount() {
        return ((Integer) service.selectAll().size()).toString();
    }

    @Override
    public String getItem(String itemId) {
        Product product = getProductBySku(itemId);
        return String.format("%s | %.2f", product.getName(), product.getPriceAsFloat());
    }
}
