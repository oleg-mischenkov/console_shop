package com.mishchenkov.entity.product.lamp;

import com.mishchenkov.annotation.Item;

import java.util.Objects;

/**
 * This object simulates an LED lamp. The lamp may have a different count of LEDs,
 * and may or may not have a radiator.
 */
public abstract class LedLamp extends Lamp {

    @Item(title = "Item.input.led", msg = "Item.select.led")
    private int ledCount;

    @Item(title = "Item.input.radiator", msg = "Item.select.radiator")
    private boolean radiator;

    protected LedLamp() {}

    public int getLedCount() {
        return ledCount;
    }

    public void setLedCount(int ledCount) {
        this.ledCount = ledCount;
    }

    public boolean isRadiator() {
        return radiator;
    }

    public void setRadiator(boolean radiator) {
        this.radiator = radiator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LedLamp ledLamp = (LedLamp) o;
        return ledCount == ledLamp.ledCount && radiator == ledLamp.radiator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ledCount, radiator);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", power=" + getPower() +
                ", colourTemperature=" + getColourTemperature() +
                ", height=" + getHeight() +
                ", diameter=" + getDiameter() +
                ", ledCount=" + getLedCount() +
                ", radiator=" + isRadiator() + System.lineSeparator();
    }
}
