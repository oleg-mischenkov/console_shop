package com.mishchenkov.entity.product;

import com.mishchenkov.annotation.Item;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    private static final long serialVersionUID = 7001001954116978482L;

    @Item(title = "Item.input.name", msg = "Item.select.name")
    private String name;

    @Item(title = "Item.input.sku", msg = "Item.select.sku")
    private String sku;

    @Item(title = "Item.input.desc", msg = "Item.select.desc")
    private String description;

    @Item(title = "Item.input.state", msg = "Item.select.state")
    private boolean state;

    @Item(title = "Item.input.price", msg = "Item.select.price")
    private int price;

    public Product() {
        description = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public float getPriceAsFloat() {
        return (float) price / 100;
    }

    public void setPrice(int price) {
        if (price < 0) throw new IllegalArgumentException("Price must be more then 0. price: " + price);
        this.price = price;
    }

    public void setPrice(float price) {
        this.price = (int) (price * 100);
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return  state == product.state &&
                price == product.price &&
                name.equals(product.name) &&
                sku.equals(product.sku) &&
                description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sku, description, state, price);
    }

    @Override
    public String toString() {
        int descriptionLength = description.length() >= 100 ? 100 : description.length();

        return String.format(
                "Product (%s) : %s %14s \'%s\'; %s %14s %s; %s %14s \'%s\'; %s %14s %.2f; %s %14s %b.",
                this.getClass().getSimpleName(),
                System.lineSeparator(),
                "Name:", name, System.lineSeparator(),
                "sku:", sku, System.lineSeparator(),
                "Description:", description.substring(0, descriptionLength) + "...", System.lineSeparator(),
                "price:", getPriceAsFloat(), System.lineSeparator(),
                "isON:", isState()
        );
    }
}
