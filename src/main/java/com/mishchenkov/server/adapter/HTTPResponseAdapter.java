package com.mishchenkov.server.adapter;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.ProductService;

public class HTTPResponseAdapter extends ResponseAdapter {


    public HTTPResponseAdapter(ProductService service) {
        super(service);
    }

    @Override
    public String getCount() {
        int productCount = service.selectAll().size();
        return String.format("{count:%d}", productCount);
    }

    @Override
    public String getItem(String itemId) {
        Product product = getProductBySku(itemId);
        return String.format("{name: %s, price: %.2f}", product.getName(), product.getPriceAsFloat());
    }
}
