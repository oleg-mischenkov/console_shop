package com.mishchenkov.strategy;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.mishchenkov.constant.Constants.CLASS_COMPARATOR;

public class RandomAnnotationStrategy implements AnnotationStrategy {

    private final Map<Class<?>, Function<String, Object>> randomMap;

    public RandomAnnotationStrategy() {
        Random random;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Can't create a Random instance. ", e);
        }

        randomMap = new TreeMap<>(CLASS_COMPARATOR);
        randomMap.put(String.class, s -> s + random.ints(0, 9)
                .limit(5)
                .mapToObj(i -> i + "")
                .collect(Collectors.joining()));
        randomMap.put(boolean.class, s -> random.nextBoolean());
        randomMap.put(int.class, s -> random.nextInt(100));
    }

    @Override
    public Object getObject(Class<?> typeClass, String name, String msg) {
        return randomMap.get(typeClass).apply(name);
    }
}
