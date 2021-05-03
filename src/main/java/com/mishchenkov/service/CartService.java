package com.mishchenkov.service;

import com.mishchenkov.entity.Cart;
import com.mishchenkov.entity.product.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class CartService {

    private final Cart cart;

    public CartService(Cart cart) {
        this.cart = cart;
    }

    public void putProduct(Product product) {
        float productFloatPrice = product.getPriceAsFloat();

        Map<Product, Integer> holder = cart.getHolder();

        if ( holder.containsKey(product) ) {

            Integer value = holder.get(product);
            holder.put(product, ++value);

        } else {

            holder.put(product, 1);
        }

        cart.setTotalPriceAsFloat(
                cart.getTotalPriceAsFloat() + productFloatPrice
        );
    }

    public float getTotalPriceAsFloat() {
        return cart.getTotalPriceAsFloat();
    }

    public Map<Product, Integer> getAllProduct() {
        return cart.getHolder();
    }

    public int getCount() {
        return cart.getHolder().size();
    }

    public void clear() {
        cart.setHolder( new LinkedHashMap<>() );
        cart.setTotalPriceAsFloat(0);
    }
}
