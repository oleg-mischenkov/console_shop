package com.mishchenkov.service.impl;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.ProductBuilder;
import com.mishchenkov.repository.ProductDAO;
import com.mishchenkov.service.Service;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static com.mishchenkov.initialization.StorageInitialization.PRODUCT_TABLE_NAME;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    @Mock
    private ProductDAO dao;
    private Service<Integer, Product> productService;

    public ProductServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(dao);
    }

    @Test
    public void shouldObtainAllProducts_selectAllTest() {
        //given
        Product[] products = {
                new ProductBuilder().setName("prod_1").setDescription("...").build(),
                new ProductBuilder().setName("prod_2").setDescription("...").build()
        };
        given( dao.selectAll(PRODUCT_TABLE_NAME) ).willReturn(
                Arrays.asList(products)
        );

        //when
        Product[] result = productService.selectAll().toArray(new Product[0]);

        //then
        Assert.assertArrayEquals(products, result);
    }

    @Test
    public void shouldObtainProductByIndex_selectElementTest() {
        //given
        Product product = new ProductBuilder().setName("prod_1").setDescription("...").build();
        given( dao.selectElement(PRODUCT_TABLE_NAME, 1) ).willReturn( product );

        //when
        Product result = productService.selectElement(1);

        //then
        Assert.assertEquals(product, result);
    }

    @Test
    public void shouldPutSomeProductToTheStorage_insertElementTest() {
        //given
        Product product = new ProductBuilder().setName("prod_1").setDescription("...").build();

        //when
        productService.insertElement(1, product);

        //then
        verify(dao).insertElement(
                PRODUCT_TABLE_NAME, 1, product
        );
    }

    @Test
    public void shouldObtainJustTwoProductsFromStorage_selectDataByDiapasonTest() {
        //given
        Map<Integer, Product> productMap = new TreeMap<>();
        productMap.put(1, new ProductBuilder().setName("prod_1").setSku("sku-1").setDescription("...").build());
        productMap.put(2, new ProductBuilder().setName("prod_2").setSku("sku-2").setDescription("...").build());
        productMap.put(3, new ProductBuilder().setName("prod_3").setSku("sku-3").setDescription("...").build());
        productMap.put(4, new ProductBuilder().setName("prod_4").setSku("sku-4").setDescription("...").build());
        productMap.put(5, new ProductBuilder().setName("prod_5").setSku("sku-5").setDescription("...").build());
        productMap.put(6, new ProductBuilder().setName("prod_6").setSku("sku-6").setDescription("...").build());


        when( dao.selectAllDataFromTable(PRODUCT_TABLE_NAME) ).thenReturn(productMap);

        //when
        Product[] result = productService.selectDataByDiapason(2, 4).values().toArray(new Product[0]);

        //then
        Product[] expected = {
                new ProductBuilder().setName("prod_2").setSku("sku-2").setDescription("...").build(),
                new ProductBuilder().setName("prod_3").setSku("sku-3").setDescription("...").build()
        };
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldObtainAllProductsFromStorage_selectAllDataTest() {
        //given
        Map<Integer, Product> productMap = new TreeMap<>();
        productMap.put(1, new ProductBuilder().setName("prod_1").setSku("sku-1").setDescription("...").build());
        productMap.put(2, new ProductBuilder().setName("prod_2").setSku("sku-2").setDescription("...").build());
        productMap.put(3, new ProductBuilder().setName("prod_3").setSku("sku-3").setDescription("...").build());
        productMap.put(4, new ProductBuilder().setName("prod_4").setSku("sku-4").setDescription("...").build());
        productMap.put(5, new ProductBuilder().setName("prod_5").setSku("sku-5").setDescription("...").build());
        productMap.put(6, new ProductBuilder().setName("prod_6").setSku("sku-6").setDescription("...").build());


        when( dao.selectAllDataFromTable(PRODUCT_TABLE_NAME) ).thenReturn(productMap);

        //when
        Product[] result = productService.selectAllData().values().toArray(new Product[0]);

        //then
        Product[] expected = {
                new ProductBuilder().setName("prod_1").setSku("sku-1").setDescription("...").build(),
                new ProductBuilder().setName("prod_2").setSku("sku-2").setDescription("...").build(),
                new ProductBuilder().setName("prod_3").setSku("sku-3").setDescription("...").build(),
                new ProductBuilder().setName("prod_4").setSku("sku-4").setDescription("...").build(),
                new ProductBuilder().setName("prod_5").setSku("sku-5").setDescription("...").build(),
                new ProductBuilder().setName("prod_6").setSku("sku-6").setDescription("...").build()

        };
        Assert.assertArrayEquals(expected, result);
    }
}