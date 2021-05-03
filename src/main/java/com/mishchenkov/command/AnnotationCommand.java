package com.mishchenkov.command;

import com.mishchenkov.creator.AnnotationCreator;
import com.mishchenkov.entity.ApplicationLocalizator;
import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.ProductService;
import com.mishchenkov.strategy.AnnotationStrategy;

import java.util.Map;
import java.util.Scanner;

public class AnnotationCommand extends AbstractCreateProductCommand implements Command {

    private static final String COMMAND_NAME = "create new product by annotated fields";

    private final AnnotationStrategy strategy;
    private final ApplicationLocalizator localizator;
    private final Scanner scanner;
    private final ProductService service;
    private final Map<Integer, Class<? extends Product>> productClasses;

    public AnnotationCommand(AnnotationStrategy strategy,
                             ApplicationLocalizator localizator,
                             Scanner scanner,
                             ProductService service,
                             Map<Integer, Class<? extends Product>> productClasses) {
        this.strategy = strategy;
        this.localizator = localizator;
        this.scanner = scanner;
        this.service = service;
        this.productClasses = productClasses;
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }

    @Override
    public void execute() {
        Class<? extends Product> productClass = selectProduct(productClasses, scanner);
        AnnotationCreator creator = new AnnotationCreator(strategy, localizator);

        Product product = creator.createProduct(productClass);

        int greatestProductKey = service.getGreatestProductKey();
        service.insertElement(++greatestProductKey, product);
    }
}
