package com.mishchenkov.entity;

import com.mishchenkov.entity.product.Product;
import org.junit.Assert;
import org.junit.Test;

public class ProductTest {

    @Test
    public void shouldObtainFairFloatValue_getPriceAsFloatTest() {
        //given
        Product product = new Product();
        int moneyInCents = 550;

        //when
        product.setPrice(moneyInCents);

        //then
        float expectedValue = 5.5f;
        float result = product.getPriceAsFloat();
        Assert.assertEquals(expectedValue, result, 0.001);
    }

    @Test
    public void shouldSetPriceLikeFloatAndGetItLikeInteger_setPriceTest() {
        //given
        Product product = new Product();
        float money = 6.58f;

        //when
        product.setPrice(money);

        //then
        int expectedPrice = 658;
        int result = product.getPrice();
        Assert.assertEquals(expectedPrice, result);
    }
}