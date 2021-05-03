package com.mishchenkov.creator;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.lamp.SmartLamp;
import com.mishchenkov.strategy.Strategy;
import com.mishchenkov.constant.Constants;

public class SmartLampCreator extends LedLampCreator {

    public SmartLampCreator(Strategy strategy) {
        super(strategy);
    }

    @Override
    public Product createProduct() {
        SmartLamp smartLamp =  new SmartLamp();

        setBasicField(smartLamp);
        smartLamp.setBluetooth(strategy.getBoolean("Bluetooth", Constants.PROD_BLUETOOTH_MSG));
        smartLamp.setWiFi(strategy.getBoolean("WiFi", Constants.PROD_WIFI_MSG));

        return smartLamp;
    }
}
