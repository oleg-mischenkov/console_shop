package com.mishchenkov.creator;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.lamp.EdisonLamp;
import com.mishchenkov.strategy.Strategy;
import com.mishchenkov.constant.Constants;

public class EdisonLampCreator extends LampCreator {

    public EdisonLampCreator(Strategy strategy) {
        super(strategy);
    }

    @Override
    public Product createProduct() {
        EdisonLamp edisonLamp = new EdisonLamp();
        setBasicField(edisonLamp);

        edisonLamp.setTwinkle(strategy.getBoolean("twinkle", Constants.PROD_TWINKLE_MSG));
        edisonLamp.setSpiralsCount(strategy.getInt("Spirals count", Constants.PROD_SPIRAL_MSG));

        return edisonLamp;
    }
}
