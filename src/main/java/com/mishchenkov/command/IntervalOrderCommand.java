package com.mishchenkov.command;

import com.mishchenkov.entity.Order;
import com.mishchenkov.service.OrderService;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;
import static com.mishchenkov.constant.Constants.ERR_MSG_LOOP;

public class IntervalOrderCommand implements Command {

    private final Logger logger = Logger.getLogger(IntervalOrderCommand.class);

    private static final String COMMAND_NAME = "show all orders for the time interval";

    private final DateValidator dateValidator;
    private final Scanner scanner;
    private final OrderService service;

    public IntervalOrderCommand(Scanner scanner, OrderService service) {
        this.scanner = scanner;
        this.service = service;
        dateValidator = new DateValidator();
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }

    @Override
    public void execute() {

        while (true) {
            Date startDate = dateValidator.readDateFromConsole(
                    scanner, "enter First date in the format: yyyy:mm:dd"
            );
            Date endDate = dateValidator.readDateFromConsole(
                    scanner, "enter Second date in the format: yyyy:mm:dd"
            );


            if (startDate.after(endDate))  {
                logger.warn(ERR_MSG_LOOP);
                continue;
            }

            Map<Date, Order> orders = service.selectDataByDiapason(startDate, endDate);

            printOrders(orders);

            break;
        }
    }

    private void printOrders(Map<Date, Order> orders) {
        logger.info(orders.toString().concat(LINE_SEPARATOR));
    }
}
