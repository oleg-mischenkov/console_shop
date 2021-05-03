package com.mishchenkov.command;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.CartService;
import org.apache.log4j.Logger;

import java.util.Map;

import static com.mishchenkov.constant.Constants.ERR_MSG_CART_IS_EMPTY;
import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;

public class ShowCartCommand implements Command {

    private final Logger logger = Logger.getLogger(ShowCartCommand.class);

    private static final String COMMAND_NAME = "show cart";

    private final CartService cartService;

    public ShowCartCommand(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }

    @Override
    public void execute() {

        if (cartService.getCount() > 0) {
            Map<Product, Integer> products = cartService.getAllProduct();
            printCartValue(products);

        } else {
            logger.warn(ERR_MSG_CART_IS_EMPTY);

        }
    }

    private void printCartValue(Map<Product, Integer> products) {
        logger.info(
                String.format(
                        "%s %-3s | %-10s | %-50s | %-5s | %-6s | %-6s |%s",
                        LINE_SEPARATOR, "N", "sku", "name", "count", "price", "total",
                        LINE_SEPARATOR
                )
        );

        int index = 0;
        for (Product element: products.keySet().toArray(new Product[0])) {
            String name = cutName(element.getName(), 45);
            int productCount = products.get(element);

            logger.info(
                    String.format(
                            " %-3d | %-10s | %-50s | %-5d | %-6.2f | %-6.2f |%s",
                            ++index, element.getSku(), name, productCount,
                            element.getPriceAsFloat(), element.getPriceAsFloat() * productCount,
                            LINE_SEPARATOR
                    )
            );
        }
    }

    private String cutName(String name, int maxLength) {
        return name.length() > maxLength ? name.substring(0, maxLength) + "..." : name;
    }
}
