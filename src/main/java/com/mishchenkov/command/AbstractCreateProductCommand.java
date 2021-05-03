package com.mishchenkov.command;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.constant.Constants;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Scanner;

public abstract class AbstractCreateProductCommand {

    private final Logger logger = Logger.getLogger(AbstractCreateProductCommand.class);

    private void printProducts(Map<Integer, Class<? extends Product>> productClasses) {
        logger.info("print command key: ".concat(Constants.LINE_SEPARATOR));

        for (Integer key: productClasses.keySet()) {
            logger.info(
                    String.format(
                            "\t %d - %s %s",
                            key,
                            productClasses.get(key).getSimpleName(),
                            Constants.LINE_SEPARATOR
                    )
            );
        }

        logger.info("enter index: ");
    }

    protected Class<? extends Product> selectProduct(Map<Integer, Class<? extends Product>> productClasses,
                                                     Scanner scanner) {
        int productCount = productClasses.size();

        while (true) {
            printProducts(productClasses);

            String line = scanner.nextLine();
            if (
                    line.matches(Constants.REGEXP_MATCH_NUMBERS) &&
                            productCount > Integer.parseInt(line)
            ) {
                int index = Integer.parseInt(line);
                return productClasses.get(index);
            }

        }
    }

}
