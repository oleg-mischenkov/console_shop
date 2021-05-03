package com.mishchenkov.entity.product.lamp;

public class SmartLampBuilder {
    private final SmartLamp lamp;

    public SmartLampBuilder() {
        lamp = new SmartLamp();
    }

    public SmartLampBuilder setName(String name) {
        lamp.setName(name);
        return this;
    }

    public SmartLampBuilder setSku(String sku) {
        lamp.setSku(sku);
        return this;
    }

    public SmartLampBuilder setDescription(String description) {
        lamp.setDescription(description);
        return this;
    }

    public SmartLampBuilder setPrice(int price) {
        lamp.setPrice(price);
        return this;
    }

    public SmartLampBuilder setPrice(float price) {
        lamp.setPrice(price);
        return this;
    }

    public SmartLampBuilder setState(boolean state) {
        lamp.setState(state);
        return this;
    }

    public SmartLampBuilder setPower(int power) {
        lamp.setPower(power);
        return this;
    }

    public SmartLampBuilder setColourTemperature(int colourTemperature) {
        lamp.setColourTemperature(colourTemperature);
        return this;
    }

    public SmartLampBuilder setHeight(int height) {
        lamp.setHeight(height);
        return this;
    }

    public SmartLampBuilder setDiameter(int diameter) {
        lamp.setDiameter(diameter);
        return this;
    }

    public SmartLampBuilder setLedCount(int ledCount) {
        lamp.setLedCount(ledCount);
        return this;
    }

    public SmartLampBuilder setRadiator(boolean radiator) {
        lamp.setRadiator(radiator);
        return this;
    }

    public SmartLampBuilder setBluetooth(boolean bluetooth) {
        lamp.setBluetooth(bluetooth);
        return this;
    }

    public SmartLampBuilder setWiFi(boolean wiFi) {
        lamp.setWiFi(wiFi);
        return this;
    }

    public SmartLamp build() {
        return lamp;
    }
}
