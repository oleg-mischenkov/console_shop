package com.mishchenkov.command;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.ProductService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;

public class ShowGoodsCommand implements Command {

    private final Logger logger = Logger.getLogger(ShowGoodsCommand.class);

    private static final String COMMAND_NAME = "show all goods";

    private final ProductService service;

    public ShowGoodsCommand(ProductService service) {
        this.service = service;
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }

    @Override
    public void execute() {
        List<Product> products = service.selectAll();

        for (Product element: products) {
            logger.info( element.toString().concat(LINE_SEPARATOR) );
        }
    }
}
