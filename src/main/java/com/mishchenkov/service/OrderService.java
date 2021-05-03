package com.mishchenkov.service;

import com.mishchenkov.entity.Order;

import java.util.Date;

public interface OrderService extends Service<Date, Order> {

    Date selectClosestDate(Date value);

}
