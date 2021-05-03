package com.mishchenkov.strategy;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomStrategy implements Strategy {

    private final Random random;

    public RandomStrategy() {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Can't create a Random instance. ", e);
        }
    }

    @Override
    public String getString(String strName, String msg) {
        return strName +
                random.ints(0, 9)
                        .limit(5)
                        .mapToObj(i -> i + "")
                        .collect(Collectors.joining());
    }

    @Override
    public int getInt(String strName, String msg) {
        return random.nextInt(100);
    }

    @Override
    public boolean getBoolean(String strName, String msg) {
        return random.nextBoolean();
    }

    @Override
    public float getFloat(String strName, String msg) {
        return random.nextFloat();
    }
}
