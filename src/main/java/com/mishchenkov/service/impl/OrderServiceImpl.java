package com.mishchenkov.service.impl;

import com.mishchenkov.entity.Order;
import com.mishchenkov.repository.OrderDAO;
import com.mishchenkov.service.OrderService;
import com.mishchenkov.initialization.StorageInitialization;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO dao;

    public OrderServiceImpl(OrderDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Order> selectAll() {
        return dao.selectAll(StorageInitialization.ORDER_TABLE_NAME);
    }

    @Override
    public Order selectElement(Date key) {
        return dao.selectElement(StorageInitialization.ORDER_TABLE_NAME, key);
    }


    @Override
    public void insertElement(Date key, Order value) {
        dao.insertElement(StorageInitialization.ORDER_TABLE_NAME, key, value);
    }

    @Override
    public Map<Date, Order> selectDataByDiapason(Date firstKey, Date secondKey) {
        SortedMap<Date, Order> map = (SortedMap<Date, Order>) dao.selectAllDataFromTable(StorageInitialization.ORDER_TABLE_NAME);
        return map.subMap(firstKey, secondKey);
    }

    @Override
    public Map<Date, Order> selectAllData() {
        return dao.selectAllDataFromTable(StorageInitialization.ORDER_TABLE_NAME);
    }


    @Override
    public Date selectClosestDate(Date date) {
        Date resultDate = null;
        NavigableMap<Date, Order> allOrder = (NavigableMap<Date, Order>) selectAllData();

        Date beforeOrderDate = allOrder.lowerKey(date);
        Date afterOrderDate = allOrder.ceilingKey(date);

        if (beforeOrderDate != null && afterOrderDate != null) {
            long beforeInterval = date.getTime() - beforeOrderDate.getTime();
            long afterInterval = afterOrderDate.getTime() - date.getTime();

            if (beforeInterval < afterInterval) {
                resultDate = beforeOrderDate;
            } else {
                resultDate = afterOrderDate;
            }

        } else if (beforeOrderDate == null) {
            resultDate = afterOrderDate;

        } else if (afterOrderDate == null){
            resultDate = beforeOrderDate;

        }

        return resultDate;
    }
}
