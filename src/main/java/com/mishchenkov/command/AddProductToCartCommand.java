package com.mishchenkov.command;

import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.CartService;
import com.mishchenkov.service.MostPopularGoodsService;
import com.mishchenkov.service.ProductService;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static com.mishchenkov.constant.Constants.ERR_MSG_IT_CONTAINS_NOT_NUMBERS;
import static com.mishchenkov.constant.Constants.ERR_MSG_IT_DOES_NOT_HAVE_AN_INDEX;
import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_NUMBERS_WITH_GAPS;

public class AddProductToCartCommand implements Command {

    private final Logger logger = Logger.getLogger(AddProductToCartCommand.class);

    private static final String COMMAND_NAME = "add product to cart";

    private final ProductService service;
    private final Scanner scanner;
    private final CartService cartService;
    private final MostPopularGoodsService popularGoodsService;

    public AddProductToCartCommand(ProductService service, Scanner scanner, CartService cartService, MostPopularGoodsService popularGoodsService) {
        this.service = service;
        this.scanner = scanner;
        this.cartService = cartService;
        this.popularGoodsService = popularGoodsService;
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }

    @Override
    public void execute() {
        Map<Integer, Product> products = service.selectAllData();

        //  show all products
        printProducts(products);

        while (true) {
            printWelcomeLine();
            String result = scanner.nextLine().trim();

            if ( result.matches(REGEXP_MATCH_NUMBERS_WITH_GAPS) ) {

                String[] tmpSeq = result.split(" ");
                Integer[] commandSeq = obtainIndexes( tmpSeq );

                if ( checkIndex(commandSeq) ) {
                    addProductToCart(commandSeq);
                    break;

                } else {
                    logger.warn(
                            String.format("%s %s",
                                    ERR_MSG_IT_DOES_NOT_HAVE_AN_INDEX,
                                    Arrays.toString(commandSeq)
                            )
                    );
                    continue;
                }

            } else {
                logger.warn(
                        String.format("%s: [%s]",
                                ERR_MSG_IT_CONTAINS_NOT_NUMBERS,
                                result
                        )
                );
            }
        }

    }

    private void addProductToCart(Integer[] indexSeq) {
        for (Integer index: indexSeq) {
            Product product = service.selectElement(index);

            cartService.putProduct( product );
            popularGoodsService.putProduct(index, product);

        }
    }

    private Integer[] obtainIndexes(String[] tmpSeq) {
        return Arrays.stream(tmpSeq)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    private boolean checkIndex(Integer[] commandSeq) {
        Set<Integer> keySet = service.selectAllData().keySet();

        return keySet.containsAll(
                Arrays.asList(commandSeq)
        );
    }

    private void printWelcomeLine() {
        logger.info("enter product index: ");
    }

    private void printProducts(Map<Integer, Product> productMap) {
        logger.info(LINE_SEPARATOR);

        for (Integer key: productMap.keySet()) {
            logger.info(
                    String.format("%-4d product sku: [ %-13s ] %s",
                            key, productMap.get(key).getSku(), LINE_SEPARATOR
                    )
            );
        }
    }
}
