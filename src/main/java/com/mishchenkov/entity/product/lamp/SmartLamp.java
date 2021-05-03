package com.mishchenkov.entity.product.lamp;

import com.mishchenkov.annotation.Item;

import java.io.Serializable;
import java.util.Objects;

/**
 * This object simulates an LED light bulb with a chip. The lamp
 * contains a Wi-Fi (and or) bluetooth connection interface.
 */
public class SmartLamp extends LedLamp implements Serializable {

    private static final long serialVersionUID = -7522529149083492642L;

    @Item(title = "Item.input.bluetooth", msg = "Item.select.bluetooth")
    private boolean bluetooth;

    @Item(title = "Item.input.wifi", msg = "Item.select.wifi")
    private boolean wiFi;

    public SmartLamp() {
        super();
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public boolean isWiFi() {
        return wiFi;
    }

    public void setWiFi(boolean wiFi) {
        this.wiFi = wiFi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartLamp smartLamp = (SmartLamp) o;
        return bluetooth == smartLamp.bluetooth && wiFi == smartLamp.wiFi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bluetooth, wiFi);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", bluetooth=" + isBluetooth() +
                ", wiFi=" + isWiFi() + System.lineSeparator();
    }
}
