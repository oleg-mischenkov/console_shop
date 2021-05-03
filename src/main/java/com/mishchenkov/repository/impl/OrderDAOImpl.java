package com.mishchenkov.repository.impl;

import com.mishchenkov.entity.Order;
import com.mishchenkov.repository.OrderDAO;
import com.mishchenkov.storage.DataBase;

import java.util.Date;

public class OrderDAOImpl extends BaseDAO<Date, Order> implements OrderDAO {

    public OrderDAOImpl(DataBase dataBase) {
        super(dataBase);
    }
}
