package com.mishchenkov.entity;

import com.mishchenkov.entity.product.Product;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Order {

    private final Map<Product, Integer> holder;

    public Order() {
        holder = new LinkedHashMap<>();
    }

    public Order(Map<Product, Integer> map) {
        this();
        holder.putAll(map);
    }

    public Order accept(Product product, Integer productCount) {
        holder.put(product, productCount);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(holder, order.holder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holder);
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();

        stringBuffer.append("Order: [").append(System.lineSeparator());

        for (Product element: holder.keySet()) {
            int count = holder.get(element);

            stringBuffer
                    .append("* count( ")
                    .append(count)
                    .append(" ) \t")
                    .append(element.toString())
                    .append(System.lineSeparator());
        }

        return stringBuffer.toString();
    }
}
