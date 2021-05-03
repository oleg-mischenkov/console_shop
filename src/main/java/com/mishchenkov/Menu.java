package com.mishchenkov;

import com.mishchenkov.command.AnnotationCommand;
import com.mishchenkov.command.Command;
import com.mishchenkov.command.CommandHolder;
import com.mishchenkov.command.CreateNewProductCommand;
import com.mishchenkov.context.ApplicationContext;
import com.mishchenkov.entity.ApplicationLocalizator;
import com.mishchenkov.entity.product.Product;
import com.mishchenkov.service.ProductService;
import com.mishchenkov.strategy.AnnotationStrategy;
import com.mishchenkov.strategy.Strategy;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_JUST_ONE_AND_ZERO;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_NUMBERS;
import static com.mishchenkov.constant.Constants.WELCOME_MSG_FOR_PROD_GENERATION_TYPE;
import static com.mishchenkov.constant.Constants.WELCOME_MSG_LANGUAGE_INDEX;

public class Menu {

    private final Logger logger = Logger.getLogger(Menu.class);

    private final Map<Integer, Class<? extends Product>> productClasses;
    private final Map<Integer, Strategy> createStrategyMap;
    private final Map<Integer, AnnotationStrategy> annotationStrategyMap;
    private final CommandHolder holder;

    public Menu(Map<Integer, AnnotationStrategy> annotationStrategyMap,
                Map<Integer, Strategy> strategyMap,
                CommandHolder holder,
                Map<Integer, Class<? extends Product>> productClasses) {
        this.annotationStrategyMap = annotationStrategyMap;
        this.createStrategyMap = strategyMap;
        this.holder = holder;
        this.productClasses = productClasses;
    }

    public void startMenu() {
        ApplicationContext context = holder.getContext();
        Scanner scanner = context.getObjectByClass(Scanner.class);
        ApplicationLocalizator localizator = context.getObjectByClass(ApplicationLocalizator.class);

        selectLocalization(scanner, localizator);

        selectProdCreationType(context, scanner, localizator);

        selectMenuItem(scanner);
    }

    private void selectLocalization(Scanner scanner, ApplicationLocalizator localizator) {
        String line;
        while (true) {
            logger.info("Language list".concat(LINE_SEPARATOR));

            printAllLanguage(localizator);

            line = scanner.next();
            if (line.matches(REGEXP_MATCH_NUMBERS)
                    && Integer.parseInt(line) < localizator.localeSize()) {

                int localeIndex = Integer.parseInt(line);
                Locale currentLocale = localizator.getAvailableLocale().get(localeIndex);
                localizator.setCurrentLocale(currentLocale);
                break;
            }
        }
    }

    private void printAllLanguage(ApplicationLocalizator localizator) {
        List<Locale> localeList = localizator.getAvailableLocale();

        IntStream.range(0, localizator.localeSize())
                .forEach(index -> logger.info(
                        String.format( "\t%-5d - %s %s",
                                index,
                                localeList.get(index).getLanguage(),
                                LINE_SEPARATOR)
                ));

        logger.info(WELCOME_MSG_LANGUAGE_INDEX);
    }

    private void selectMenuItem(Scanner scanner) {
        printAllCommands(holder);

        String line;

        while (true) {
            line = scanner.nextLine();

            if ( line.matches(REGEXP_MATCH_NUMBERS) ) {
                handleCommand(holder, line);

            } else {
                if (line.equals("exit")) {
                    break;
                } else if (line.length() > 0) {
                    logger.info(
                            String.format("The command [%s] is incorrect. Try again %s",
                                    line,
                                    LINE_SEPARATOR)
                    );
                }
            }

            if (line.length() > 0) printAllCommands(holder);
        }
    }

    private void selectProdCreationType(ApplicationContext context, Scanner scanner, ApplicationLocalizator localizator) {
        ProductService service = context.getObjectByClass(ProductService.class);

        String line;
        int productGenerationType;

        while (true) {
            logger.info(WELCOME_MSG_FOR_PROD_GENERATION_TYPE);

            line = scanner.next();
            if (line.matches(REGEXP_MATCH_JUST_ONE_AND_ZERO)) {
                productGenerationType = Integer.parseInt(line);

                Command productCreateCommand = new CreateNewProductCommand(
                        createStrategyMap.get(productGenerationType),
                        scanner,
                        service,
                        productClasses
                );

                Command annotationCommand = new AnnotationCommand(
                        annotationStrategyMap.get(productGenerationType),
                        localizator,
                        scanner,
                        service,
                        productClasses
                );

                int newCommandKey = holder.getAllCommand().size();
                holder.setCommand(newCommandKey++, productCreateCommand);
                holder.setCommand(newCommandKey, annotationCommand);

                break;
            }
        }
    }

    private void handleCommand(CommandHolder holder, String inputLine) {
        int commandKey = Integer.parseInt(inputLine);

        if (holder.isCommandExist(commandKey)) {
            holder.getCommand(commandKey).execute();
        } else {
            logger.warn("Ops");
        }
    }

    private void printAllCommands(CommandHolder holder) {
        logger.info(LINE_SEPARATOR);

        for (Integer key: holder.getAllCommand().keySet()) {
            logger.info(
                    String.format(
                            "%-5d %s %s",
                            key,
                            holder.getCommand(key).getDescription() ,
                            LINE_SEPARATOR
                    )
            );
        }

        logger.info(
                String.format(
                        "%-5s is system EXIT %s",
                        "exit",
                        LINE_SEPARATOR
                )
        );

        logger.info("print command key: ");
    }

}
