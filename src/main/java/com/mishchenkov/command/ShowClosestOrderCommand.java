package com.mishchenkov.command;

import com.mishchenkov.service.OrderService;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Scanner;

import static com.mishchenkov.constant.Constants.ERR_MSG_IT_DOES_NOT_HAVE_CLOSE_ORDER;
import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;

public class ShowClosestOrderCommand implements Command {

    private final Logger logger = Logger.getLogger(ShowClosestOrderCommand.class);

    private static final String COMMAND_NAME = "show close order";

    private final DateValidator dateValidator;
    private final Scanner scanner;
    private final OrderService service;

    public ShowClosestOrderCommand(Scanner scanner, OrderService service) {
        this.scanner = scanner;
        this.service = service;
        dateValidator = new DateValidator();
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }

    @Override
    public void execute() {
        Date date = dateValidator.readDateFromConsole(
                scanner, "enter date in the format: yyyy:mm:dd"
        );

        Date closeDate = service.selectClosestDate(date);

        if (closeDate != null) {
            printTrueOrder(closeDate);

        } else {
            logger.warn(ERR_MSG_IT_DOES_NOT_HAVE_CLOSE_ORDER);
        }

    }

    private void printTrueOrder(Date foundDate) {
        logger.info(
                String.format(
                        "%ty:%tm:%td %s \t %s%s",
                        foundDate, foundDate, foundDate,
                        System.lineSeparator(),
                        foundDate.toString(),
                        LINE_SEPARATOR
                )
        );
    }
}
