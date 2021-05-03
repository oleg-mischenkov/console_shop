package com.mishchenkov.strategy;

import org.apache.log4j.Logger;

import java.util.Scanner;

import static com.mishchenkov.constant.Constants.REGEXP_MATCH_BOOLEAN;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_FLOAT;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_NUMBERS;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_STRING;

public class ManualStrategy implements Strategy {

    private final Logger logger = Logger.getLogger(ManualStrategy.class);

    private final Scanner scanner;

    public ManualStrategy(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getString(String strName, String msg) {
        return readLine(msg, REGEXP_MATCH_STRING);
    }

    @Override
    public int getInt(String strName, String msg) {
        return Integer.parseInt(
                readLine(msg, REGEXP_MATCH_NUMBERS)
        );
    }

    @Override
    public boolean getBoolean(String strName, String msg) {
        return Boolean.parseBoolean(
                readLine(msg, REGEXP_MATCH_BOOLEAN)
        );
    }

    @Override
    public float getFloat(String strName, String msg) {
        return Float.parseFloat(
                readLine(msg, REGEXP_MATCH_FLOAT)
        );
    }

    private String readLine(String msg, String regexp) {
        String line;

        while (true) {
            logger.info(msg);
            line = scanner.nextLine();

            if (line.matches(regexp)) {
                break;
            }
        }
        return line;
    }
}
