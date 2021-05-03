package com.mishchenkov.initialization;

import com.mishchenkov.entity.Order;
import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.ProductBuilder;
import com.mishchenkov.storage.BaseTable;
import com.mishchenkov.storage.DataBase;
import com.mishchenkov.storage.OrderTable;
import com.mishchenkov.storage.ProductTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class StorageInitialization {

    public static final String PRODUCT_TABLE_NAME = "products";
    public static final String ORDER_TABLE_NAME = "orders";

    private final List<Product> productList;

    public StorageInitialization(String fileName) {
        File dbFile = new File(fileName);

        if (dbFile.exists()) {
            this.productList = readProductsFromFile(dbFile);
        } else {
            this.productList = generateProductList();
        }
    }

    public DataBase init() {
        DataBase dataBase = new DataBase();

        BaseTable<Integer, Product> products = new ProductTable( productList );

        BaseTable<Date, Order> orders = new OrderTable();
        orders.insert(
                new GregorianCalendar(2020, Calendar.AUGUST, 18, 12, 30).getTime(),
                new Order()
                        .accept(productList.get(9), 1)
                        .accept(productList.get(8), 1)
                        .accept(productList.get(7), 1)
        );

        orders.insert(
                new GregorianCalendar(2020, Calendar.SEPTEMBER, 1, 17, 10).getTime(),
                new Order()
                        .accept(productList.get(0), 2)
                        .accept(productList.get(1), 1)
        );

        dataBase.setTable(PRODUCT_TABLE_NAME, products);
        dataBase.setTable(ORDER_TABLE_NAME, orders);

        return dataBase;
    }

    private List<Product> readProductsFromFile(File productDb) {
        List<Product> result = new ArrayList<>();

        try ( ObjectInput objectInput = new ObjectInputStream(new FileInputStream(productDb)) ) {

            List<Product> products = (List<Product>) objectInput.readObject();
            result.addAll(products);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private List<Product> generateProductList() {
        ProductBuilder productBuilder = new ProductBuilder();

        return Arrays.asList(
                productBuilder.clear()
                        .setName("Effective Java 3rd Edition")
                        .setSku("0134685997")
                        .setDescription("Since this Jolt-award winning classic was last updated in 2008, " +
                                "the Java programming environment has changed dramatically.")
                        .setState(true)
                        .setPrice(29.69f)
                        .build(),
                productBuilder.clear()
                        .setName("Head First Java, 2nd Edition 2nd Edition")
                        .setSku("0596009208")
                        .setDescription("Learning a complex new language is no easy task especially when " +
                                "it s an object-oriented computer programming language like Java.")
                        .setState(true)
                        .setPrice(21.49f)
                        .build(),
                productBuilder.clear()
                        .setName("Java: The Complete Reference, Eleventh Edition 11th Edition")
                        .setSku("1260440230")
                        .setDescription("Fully updated for Java SE 11, Java: The Complete Reference, Eleventh " +
                                "Edition explains how to develop, compile, debug, and run Java programs.")
                        .setState(true)
                        .setPrice(39.99f)
                        .build(),
                productBuilder.clear()
                        .setName("Learning Java: An Introduction to Real-World Programming with Java 5th Edition")
                        .setSku("1492056278")
                        .setDescription("If you’re new to Java—or new to programming—this best-selling book " +
                                "will guide you through the language features and APIs of Java 11")
                        .setState(true)
                        .setPrice(39.22f)
                        .build(),
                productBuilder.clear()
                        .setName("Core Java Volume I--Fundamentals (Core Series) 11th Edition")
                        .setSku("0135166306")
                        .setDescription("For serious programmers, Core Java, Volume I―Fundamentals, Eleventh " +
                                "Edition, is the definitive guide to writing robust, maintainable code.")
                        .setState(true)
                        .setPrice(27.99f)
                        .build(),
                productBuilder.clear()
                        .setName("Java: Learn Java in One Day and Learn It Well.")
                        .setSku("1790789877")
                        .setDescription("Have you always wanted to learn computer programming but are afraid " +
                                "it'll be too difficult for you?")
                        .setState(true)
                        .setPrice(2.99f)
                        .build(),
                productBuilder.clear()
                        .setName("Java Cookbook: Problems and Solutions for Java Developers 4th")
                        .setSku("1492072583")
                        .setDescription("Java continues to grow and evolve, and this cookbook continues " +
                                "to evolve in tandem.")
                        .setState(true)
                        .setPrice(45.99f)
                        .build(),
                productBuilder.clear()
                        .setName("Java All-in-One For Dummies, 6th Edition")
                        .setSku("111968045X")
                        .setDescription("Knowing Java is a must-have programming skill for any programmer. " +
                                "It’s used in a wide array of programming projects")
                        .setState(true)
                        .setPrice(36.99f)
                        .build(),
                productBuilder.clear()
                        .setName("Introduction to Java Programming and Data Structures")
                        .setSku("9780134670")
                        .setDescription("This text is intended for a 1-semester CS1 course sequence. The Brief " +
                                "Version contains the first 18 chapters of the Comprehensive Version.")
                        .setState(true)
                        .setPrice(173.32f)
                        .build(),
                productBuilder.clear()
                        .setName("Java: A Beginner's Guide, Eighth Edition 8th Edition")
                        .setSku("1260440214")
                        .setDescription("Fully updated for Java Platform, Standard Edition 11 (Java SE 11), " +
                                "Java: A Beginner’s Guide, Eighth Edition gets you started programming in " +
                                "Java right away.")
                        .setState(true)
                        .setPrice(25.52f)
                        .build()
        );
    }
}
