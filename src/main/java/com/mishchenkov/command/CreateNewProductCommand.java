package com.mishchenkov.command;

import com.mishchenkov.creator.Creator;
import com.mishchenkov.creator.EdisonLampCreator;
import com.mishchenkov.creator.ProductCreator;
import com.mishchenkov.creator.SmartLampCreator;
import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.lamp.EdisonLamp;
import com.mishchenkov.entity.product.lamp.SmartLamp;
import com.mishchenkov.service.ProductService;
import com.mishchenkov.strategy.Strategy;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CreateNewProductCommand extends AbstractCreateProductCommand implements Command {

    private static final String COMMAND_NAME = "create new product";

    private final Scanner scanner;
    private final ProductService service;
    private final Map<Integer, Class<? extends Product>> productClasses;
    private final Map<Class<? extends Product>, Creator> creatorMap;

    public CreateNewProductCommand(Strategy strategy,
                                   Scanner scanner,
                                   ProductService service,
                                   Map<Integer, Class<? extends Product>> productClasses) {
        this.scanner = scanner;
        this.service = service;
        this.productClasses = productClasses;

        creatorMap = new TreeMap<>(Comparator.comparing(Class::getSimpleName, String::compareTo));
        creatorMap.put(Product.class, new ProductCreator(strategy));
        creatorMap.put(EdisonLamp.class, new EdisonLampCreator(strategy));
        creatorMap.put(SmartLamp.class, new SmartLampCreator(strategy));
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }

    @Override
    public void execute() {
        Class<? extends Product> productClass = selectProduct(productClasses, scanner);
        Product currentProduct = creatorMap.get(productClass).createProduct();

        int greatestProductKey = service.getGreatestProductKey();

        service.insertElement(++greatestProductKey, currentProduct);
    }
}
