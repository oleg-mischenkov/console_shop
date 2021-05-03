package com.mishchenkov.creator;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.lamp.LedLamp;
import com.mishchenkov.strategy.Strategy;
import com.mishchenkov.constant.Constants;

public abstract class LedLampCreator extends ProductCreator {

    protected LedLampCreator(Strategy strategy) {
        super(strategy);
    }

    @Override
    protected void setBasicField(Product product) {
        super.setBasicField(product);

        LedLamp ledLamp = (LedLamp) product;
        ledLamp.setLedCount(strategy.getInt("Count", Constants.PROD_LED_COUNT_MSG));
        ledLamp.setRadiator(strategy.getBoolean("Radiator", Constants.PROD_RADIATOR_MSG));
    }
}
