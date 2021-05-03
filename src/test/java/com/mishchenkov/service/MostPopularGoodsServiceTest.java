package com.mishchenkov.service;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.ProductBuilder;
import org.junit.Assert;
import org.junit.Test;

public class MostPopularGoodsServiceTest {

    @Test
    public void shouldObtainTwoMostPopularProducts_getAllProductsTest() {
        //given
        MostPopularGoodsService service = new MostPopularGoodsService(2);

        Product firstProduct = new ProductBuilder().setName("prod_1").setSku("sku-1").setDescription("...").build();
        Product secondProduct = new ProductBuilder().setName("prod_2").setSku("sku-2").setDescription("...").build();
        Product thirdProduct = new ProductBuilder().setName("prod_3").setSku("sku-3").setDescription("...").build();

        //when
        service.putProduct(1, firstProduct);
        service.putProduct(2, secondProduct);
        service.putProduct(3, thirdProduct);

        //then
        Product[] expected = {secondProduct, thirdProduct};
        Product[] result = service.getAllProducts();
        Assert.assertArrayEquals(expected, result);
    }

}