package com.mishchenkov.strategy;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;

import static com.mishchenkov.constant.Constants.CLASS_COMPARATOR;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_BOOLEAN;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_NUMBERS;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_STRING;

public class ManualAnnotationStrategy implements AnnotationStrategy {

    private final Logger logger = Logger.getLogger(ManualAnnotationStrategy.class);

    private final Scanner scanner;
    private final Map<Class<?>, String> regexpMap;
    private final Map<Class<?>, Function<String, ?>> parseValueMap;

    public ManualAnnotationStrategy(Scanner scanner) {
        this.scanner = scanner;

        regexpMap = new TreeMap<>(CLASS_COMPARATOR);
        regexpMap.put(String.class, REGEXP_MATCH_STRING);
        regexpMap.put(boolean.class, REGEXP_MATCH_BOOLEAN);
        regexpMap.put(int.class, REGEXP_MATCH_NUMBERS);

        parseValueMap = new TreeMap<>(CLASS_COMPARATOR);
        parseValueMap.put(String.class, o -> o);
        parseValueMap.put(boolean.class, Boolean::parseBoolean);
        parseValueMap.put(int.class, Integer::parseInt);
    }

    @Override
    public Object getObject(Class<?> typeClass, String name, String msg) {
        String result = readLine(msg, regexpMap.get(typeClass));

        return parseValueMap.get(typeClass).apply(result);
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
