package com.mishchenkov.creator;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.lamp.Lamp;
import com.mishchenkov.strategy.Strategy;
import com.mishchenkov.constant.Constants;

public abstract class LampCreator extends ProductCreator {

    protected LampCreator(Strategy strategy) {
        super(strategy);
    }

    @Override
    protected void setBasicField(Product product) {
        super.setBasicField(product);

        Lamp lamp = (Lamp) product;
        lamp.setDiameter(strategy.getInt("Diameter", Constants.PROD_DIAMETER_MSG));
        lamp.setHeight(strategy.getInt("Height", Constants.PROD_HEIGHT_MSG));
        lamp.setPower(strategy.getInt("Power", Constants.PROD_POWER_MSG));
    }
}
