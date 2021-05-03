package com.mishchenkov.entity.product.lamp;

import com.mishchenkov.annotation.Item;
import com.mishchenkov.entity.product.Product;

import java.util.Objects;

/**
 * The class reflects an ordinary light lamp.
 */
public abstract class Lamp extends Product {

    @Item(title = "Item.input.power", msg = "Item.select.power")
    private int power;
    private int colourTemperature;
    private int height;
    private int diameter;

    protected Lamp() {}

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getColourTemperature() {
        return colourTemperature;
    }

    public void setColourTemperature(int colourTemperature) {
        this.colourTemperature = colourTemperature;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lamp lamp = (Lamp) o;
        return power == lamp.power && colourTemperature == lamp.colourTemperature && height == lamp.height && diameter == lamp.diameter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), power, colourTemperature, height, diameter);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", power=" + getPower() +
                ", colourTemperature=" + getColourTemperature() +
                ", height=" + getHeight() +
                ", diameter=" + getDiameter()
                + System.lineSeparator();
    }
}
