package com.mishchenkov.command;

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_DATE;

public class DateValidator {

    private final Logger logger = Logger.getLogger(DateValidator.class);

    private Integer year = 0;
    private Integer month = 0;
    private Integer day = 0;
    private Date userTypeDate = null;

    protected Date readDateFromConsole(Scanner scanner, String welcomeLine) {

        while (true) {
            logger.info(welcomeLine.concat(LINE_SEPARATOR));
            logger.info("enter date: ");

            scanner.hasNext();
            String result = scanner.next();

            year = 0;
            month = 0;
            day = 0;

            if ( checkMask(result) && checkDate() && checkDateDiapason() ) {
                break;
            } else {
                logger.warn(
                        String.format("Entered date [%s] shouldn't be more then today and less then 1970:01:01.%s",
                                result,
                                LINE_SEPARATOR
                        )
                );
            }
        }

        return userTypeDate;
    }

    private boolean checkDateDiapason() {
        Date today = new GregorianCalendar().getTime();
        return !userTypeDate.after(today);
    }

    private boolean checkDate() {
        if ( year >= 1970
                && (month > 0 && month < 13)
                && (day > 0 && day < 32)) {

            userTypeDate = new GregorianCalendar(year, month - 1, day, 0, 0).getTime();

            return true;
        }

        return false;
    }

    private boolean checkMask(String result) {
        if ( result.matches(REGEXP_MATCH_DATE) ) {
            String[] dateSequence = result.split(":");

            year = Integer.parseInt( dateSequence[0] );
            month = Integer.parseInt( dateSequence[1] );
            day = Integer.parseInt( dateSequence[2] );

            return true;
        }

        return false;
    }

}