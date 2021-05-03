package com.mishchenkov.initialization;

import com.mishchenkov.context.ApplicationContext;
import com.mishchenkov.entity.ApplicationLocalizator;
import com.mishchenkov.entity.Cart;
import com.mishchenkov.repository.OrderDAO;
import com.mishchenkov.repository.ProductDAO;
import com.mishchenkov.repository.impl.OrderDAOImpl;
import com.mishchenkov.repository.impl.ProductDAOImpl;
import com.mishchenkov.service.CartService;
import com.mishchenkov.service.MostPopularGoodsService;
import com.mishchenkov.service.OrderService;
import com.mishchenkov.service.ProductService;
import com.mishchenkov.service.impl.OrderServiceImpl;
import com.mishchenkov.service.impl.ProductServiceImpl;
import com.mishchenkov.storage.DataBase;

import java.util.Scanner;

import static com.mishchenkov.constant.Constants.PRODUCT_DB_FILE;

public class ApplicationContextInitialization {

    public ApplicationContext init() {
        ApplicationContext context = new ApplicationContext();

        //  add productService
        StorageInitialization storageInitialization = new StorageInitialization(PRODUCT_DB_FILE);
        DataBase dataBase = storageInitialization.init();

        ProductDAO productDAO = new ProductDAOImpl(dataBase);
        ProductService productService = new ProductServiceImpl( productDAO );
        context.putObject(ProductService.class, productService);

        //  add orderService
        OrderDAO orderDAO = new OrderDAOImpl(dataBase);
        OrderService orderService = new OrderServiceImpl( orderDAO );
        context.putObject(OrderService.class, orderService);

        //  add scanner
        Scanner scanner = new Scanner(System.in);
        context.putObject(Scanner.class, scanner);

        //  add cartService
        Cart cart = new Cart();
        CartService cartService = new CartService(cart);
        context.putObject(CartService.class, cartService);

        //  add most popular goods
        MostPopularGoodsService popularGoodsService = new MostPopularGoodsService(5);
        context.putObject(MostPopularGoodsService.class, popularGoodsService);

        //  add localizator
        ApplicationLocalizatorInitialization applicationLocalizator = new ApplicationLocalizatorInitialization();
        ApplicationLocalizator localizator = applicationLocalizator.init();

        context.putObject(ApplicationLocalizator.class, localizator);

        return context;
    }

}
