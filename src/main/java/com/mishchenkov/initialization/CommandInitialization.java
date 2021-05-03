package com.mishchenkov.initialization;

import com.mishchenkov.command.AddProductToCartCommand;
import com.mishchenkov.command.CommandHolder;
import com.mishchenkov.command.FiveLastCartGoodsCommand;
import com.mishchenkov.command.IntervalOrderCommand;
import com.mishchenkov.command.OrderCommand;
import com.mishchenkov.command.ShowCartCommand;
import com.mishchenkov.command.ShowClosestOrderCommand;
import com.mishchenkov.command.ShowGoodsCommand;
import com.mishchenkov.context.ApplicationContext;
import com.mishchenkov.service.CartService;
import com.mishchenkov.service.MostPopularGoodsService;
import com.mishchenkov.service.OrderService;
import com.mishchenkov.service.ProductService;

import java.util.Scanner;

public class CommandInitialization {

    public CommandHolder init(ApplicationContext context) {
        CommandHolder holder = new CommandHolder(context);

        ProductService productService = context.getObjectByClass(ProductService.class);
        OrderService orderService = context.getObjectByClass(OrderService.class);
        Scanner scanner = context.getObjectByClass(Scanner.class);
        CartService cartService = context.getObjectByClass(CartService.class);
        MostPopularGoodsService popularGoods = context.getObjectByClass(MostPopularGoodsService.class);

        holder.setCommand(1, new ShowGoodsCommand(productService));
        holder.setCommand(2, new AddProductToCartCommand(
                productService,
                scanner,
                cartService,
                popularGoods
        ));
        holder.setCommand(3, new ShowCartCommand(cartService));
        holder.setCommand(4, new OrderCommand(
                cartService,
                scanner,
                orderService
        ));
        holder.setCommand(5, new FiveLastCartGoodsCommand(popularGoods));
        holder.setCommand(6, new IntervalOrderCommand(
                scanner,
                orderService
        ));
        holder.setCommand(7, new ShowClosestOrderCommand(
                scanner,
                orderService
        ));

        return holder;
    }

}
