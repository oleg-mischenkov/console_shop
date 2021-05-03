package com.mishchenkov;

import com.mishchenkov.command.CommandHolder;
import com.mishchenkov.context.ApplicationContext;
import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.lamp.EdisonLamp;
import com.mishchenkov.entity.product.lamp.SmartLamp;
import com.mishchenkov.initialization.ApplicationContextInitialization;
import com.mishchenkov.initialization.CommandInitialization;
import com.mishchenkov.server.HTTPServer;
import com.mishchenkov.server.Server;
import com.mishchenkov.server.ServerFactory;
import com.mishchenkov.server.TCPServer;
import com.mishchenkov.service.ProductService;
import com.mishchenkov.strategy.AnnotationStrategy;
import com.mishchenkov.strategy.ManualAnnotationStrategy;
import com.mishchenkov.strategy.ManualStrategy;
import com.mishchenkov.strategy.RandomAnnotationStrategy;
import com.mishchenkov.strategy.RandomStrategy;
import com.mishchenkov.strategy.Strategy;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static com.mishchenkov.constant.Constants.ERR_MSG_CANT_SAVE_FILE;
import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;
import static com.mishchenkov.constant.Constants.PRODUCT_DB_FILE;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main( String[] args ) {

        ApplicationContextInitialization contextInitialization = new ApplicationContextInitialization();
        ApplicationContext context = contextInitialization.init();

        CommandInitialization commandInitialization = new CommandInitialization();
        CommandHolder holder = commandInitialization.init(context);

        ProductService productService = context.getObjectByClass(ProductService.class);

        Server tcpServer = ServerFactory.getServer(TCPServer.class, productService);
        Server httpServer = ServerFactory.getServer(HTTPServer.class, productService);
        tcpServer.startServer();
        httpServer.startServer();

        Scanner scanner = context.getObjectByClass(Scanner.class);

        Map<Integer, Class<? extends Product>> productClasses = new TreeMap<>();
        productClasses.put(0, Product.class);
        productClasses.put(1, EdisonLamp.class);
        productClasses.put(2, SmartLamp.class);

        Map<Integer, Strategy> strategyMap = new TreeMap<>();
        strategyMap.put(0, new ManualStrategy(scanner));
        strategyMap.put(1, new RandomStrategy());

        Map<Integer, AnnotationStrategy> annotationStrategyMap = new TreeMap<>();
        annotationStrategyMap.put(0, new ManualAnnotationStrategy(scanner));
        annotationStrategyMap.put(1, new RandomAnnotationStrategy());

        Menu menu = new Menu(annotationStrategyMap, strategyMap, holder, productClasses);
        menu.startMenu();

        saveProductToFile( context.getObjectByClass(ProductService.class) );
        LOGGER.info("Save DB to the file.".concat(LINE_SEPARATOR));

        tcpServer.stopServer();
        httpServer.stopServer();
    }

    private static void saveProductToFile(ProductService service) {
        List<Product> productList = service.selectAll();

        try (ObjectOutput output = new ObjectOutputStream(new FileOutputStream(PRODUCT_DB_FILE))) {
            output.writeObject(productList);
            output.flush();

        } catch (IOException e) {
            LOGGER.error(ERR_MSG_CANT_SAVE_FILE, e);
            throw new IllegalStateException(ERR_MSG_CANT_SAVE_FILE, e);
        }
    }
}