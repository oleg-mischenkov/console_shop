package com.mishchenkov.service.impl;

import com.mishchenkov.entity.Order;
import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.ProductBuilder;
import com.mishchenkov.repository.OrderDAO;
import com.mishchenkov.service.Service;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static com.mishchenkov.initialization.StorageInitialization.ORDER_TABLE_NAME;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @Mock
    private OrderDAO dao;
    private Service<Date, Order> orderService;

    public OrderServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(dao);
    }

    @Test
    public void shouldObtainAllOrders_selectAllTest() {
        //given
        Order[] orders = {
               new Order(),
               new Order()
        };
        given( dao.selectAll(ORDER_TABLE_NAME) ).willReturn(
                Arrays.asList(orders)
        );

        //when
        Order[] result = orderService.selectAll().toArray(new Order[0]);

        //then
        Assert.assertArrayEquals(orders, result);
    }

    @Test
    public void shouldObtainOrderByIndex_selectElementTest() {
        //given
        Order order = new Order();
        Date date = new Date();
        given( dao.selectElement(ORDER_TABLE_NAME, date) ).willReturn(order);

        //when
        Order result = orderService.selectElement(
                date
        );

        //then
        Assert.assertEquals(order, result);
    }

    @Test
    public void shouldPutSomeOrderToTheStorage_insertElementTest() {
        //when
        Order order = new Order();
        Date date = new Date();
        orderService.insertElement(date, order);

        //then
        verify(dao).insertElement(ORDER_TABLE_NAME, date, order);
    }

    @Test
    public void shouldObtainJustTwoOrdersFromStorage_selectDataByDiapasonTest() {
        //given
        Map<Product, Integer> firstProductMap = new LinkedHashMap<>();
        firstProductMap.put(new ProductBuilder().setName("prod_1").setSku("sku-1").setDescription("...").build(), 1);
        firstProductMap.put(new ProductBuilder().setName("prod_2").setSku("sku-2").setDescription("...").build(), 3);

        Map<Product, Integer> secondProductMap = new LinkedHashMap<>();
        secondProductMap.put(new ProductBuilder().setName("prod_5").setSku("sku-5").setDescription("...").build(), 1);
        secondProductMap.put(new ProductBuilder().setName("prod_6").setSku("sku-6").setDescription("...").build(), 1);

        Map<Date, Order> orderMap = new TreeMap<>();
        orderMap.put(new GregorianCalendar(2001, 1, 1).getTime(), new Order(firstProductMap));
        orderMap.put(new GregorianCalendar(2002, 1, 1).getTime(), new Order(secondProductMap));

        when( dao.selectAllDataFromTable(ORDER_TABLE_NAME) ).thenReturn(orderMap);

        //when
        Order[] result = orderService.selectDataByDiapason(
                new GregorianCalendar(2000, 1, 1).getTime(),
                new GregorianCalendar(2020, 1, 1).getTime()
        ).values().toArray(new Order[0]);

        //then
        Order[] expected = {
                new Order(firstProductMap), new Order(secondProductMap)
        };
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldObtainAllOrdersFromStorage_selectAllDataTest() {
        //given
        Map<Product, Integer> firstProductMap = new LinkedHashMap<>();
        firstProductMap.put(new ProductBuilder().setName("prod_1").setSku("sku-1").setDescription("...").build(), 1);
        firstProductMap.put(new ProductBuilder().setName("prod_2").setSku("sku-2").setDescription("...").build(), 3);

        Map<Product, Integer> secondProductMap = new LinkedHashMap<>();
        secondProductMap.put(new ProductBuilder().setName("prod_5").setSku("sku-5").setDescription("...").build(), 1);
        secondProductMap.put(new ProductBuilder().setName("prod_6").setSku("sku-6").setDescription("...").build(), 1);

        Map<Date, Order> orderMap = new TreeMap<>();
        orderMap.put(new GregorianCalendar(2001, 1, 1).getTime(), new Order(firstProductMap));
        orderMap.put(new GregorianCalendar(2002, 1, 1).getTime(), new Order(secondProductMap));

        when( dao.selectAllDataFromTable(ORDER_TABLE_NAME) ).thenReturn(orderMap);

        //when
        Order[] result = orderService.selectAllData().values().toArray(new Order[0]);

        //then
        Order[] expected = {
                new Order(firstProductMap), new Order(secondProductMap)
        };
        Assert.assertArrayEquals(expected, result);
    }
}