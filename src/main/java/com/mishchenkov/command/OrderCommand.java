package com.mishchenkov.command;

import com.mishchenkov.entity.Order;
import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.CartService;
import com.mishchenkov.service.OrderService;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import static com.mishchenkov.constant.Constants.ERR_MSG_CART_IS_EMPTY;
import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;

public class OrderCommand implements Command {

    private final Logger logger = Logger.getLogger(OrderCommand.class);

    private static final String COMMAND_NAME = "make an order";

    private final CartService cart;
    private final Scanner scanner;
    private final OrderService service;
    private final DateValidator dateValidator;

    public OrderCommand(CartService cart, Scanner scanner, OrderService service) {
        this.cart = cart;
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

        if (cart.getCount() > 0) {

            Date userTypeDate = dateValidator.readDateFromConsole(
                    scanner, "enter an order date in format: yyyy:mm:dd"
            );

            Map<Product, Integer> productsFromCart = cart.getAllProduct();
            Order order = new Order( productsFromCart );

            service.insertElement(userTypeDate, order);

            logger.info(
                    String.format(
                            "Total price: %.2f %s",
                            cart.getTotalPriceAsFloat(),
                            LINE_SEPARATOR
                    )
            );

            cart.clear();

        } else {
            logger.warn(ERR_MSG_CART_IS_EMPTY);
        }
    }
}