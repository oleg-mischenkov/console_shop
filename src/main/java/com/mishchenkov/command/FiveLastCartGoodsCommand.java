package com.mishchenkov.command;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.MostPopularGoodsService;
import org.apache.log4j.Logger;

import static com.mishchenkov.constant.Constants.ERR_MSG_CART_IS_EMPTY;
import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;

public class FiveLastCartGoodsCommand implements Command {

    private final Logger logger = Logger.getLogger(FiveLastCartGoodsCommand.class);

    private static final String COMMAND_NAME = "show 5 last items in a cart";

    private final MostPopularGoodsService popularGoodsService;

    public FiveLastCartGoodsCommand(MostPopularGoodsService popularGoodsService) {
        this.popularGoodsService = popularGoodsService;
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }

    @Override
    public void execute() {
        if (popularGoodsService.getCount() > 0) {
            printProducts(popularGoodsService);

        } else {
            logger.warn(ERR_MSG_CART_IS_EMPTY);
        }
    }

    private void printProducts(MostPopularGoodsService service) {
        Product[] products = service.getAllProducts();

        Product product;
        int index = 5;

        for (int i = products.length - 1; i >=0 && index-- > 0  ; i--) {
            product = products[i];

            logger.info(
                    String.format(
                            "\t* %10s | %6.2f | %s%s",
                            product.getSku(),
                            product.getPriceAsFloat(),
                            product.getName(),
                            LINE_SEPARATOR
                    )
            );
        }
    }
}
