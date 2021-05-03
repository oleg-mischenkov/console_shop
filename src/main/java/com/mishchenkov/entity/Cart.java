package com.mishchenkov.entity;

import com.mishchenkov.entity.product.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private float totalPriceAsFloat;

    private Map<Product, Integer> holder;

    public Cart() {
        holder = new LinkedHashMap<>();
    }

    public float getTotalPriceAsFloat() {
        return totalPriceAsFloat;
    }

    public void setTotalPriceAsFloat(float totalPriceAsFloat) {
        this.totalPriceAsFloat = totalPriceAsFloat;
    }

    public Map<Product, Integer> getHolder() {
        return holder;
    }

    public void setHolder(Map<Product, Integer> holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "holder=" + holder +
                '}';
    }
}
