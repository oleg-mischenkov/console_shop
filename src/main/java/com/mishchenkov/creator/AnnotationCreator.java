package com.mishchenkov.creator;

import com.mishchenkov.annotation.Item;
import com.mishchenkov.entity.ApplicationLocalizator;
import com.mishchenkov.entity.product.Product;
import com.mishchenkov.entity.product.lamp.EdisonLamp;
import com.mishchenkov.entity.product.lamp.SmartLamp;
import com.mishchenkov.strategy.AnnotationStrategy;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class AnnotationCreator {

    private final Logger logger = Logger.getLogger(AnnotationCreator.class);

    private final AnnotationStrategy strategy;
    private final ApplicationLocalizator localizator;
    private final Map<Class<? extends Product>, Supplier<? extends Product>> productMap;

    public AnnotationCreator(AnnotationStrategy strategy, ApplicationLocalizator localizator) {
        this.strategy = strategy;
        this.localizator = localizator;

        productMap = new TreeMap<>(Comparator.comparing(Class::getSimpleName, String::compareTo));
        productMap.put(Product.class, Product::new);
        productMap.put(EdisonLamp.class, EdisonLamp::new);
        productMap.put(SmartLamp.class, SmartLamp::new);
    }

    public Product createProduct(Class<? extends Product> prodClass) {
        Object product = productMap.get(prodClass).get();

        List<Field> allFields = getAllAnnotatedField(prodClass, Item.class, new ArrayList<>());

        for (Field field: allFields) {
            field.setAccessible(true);

            Class<?> fieldType = field.getType();

            Item itemClass = field.getAnnotation(Item.class);

            String title = localizator.getBundleString( itemClass.title() );
            String msg = localizator.getBundleString( itemClass.msg() );

            Object value = strategy.getObject(fieldType, title, msg);
            try {
                field.set(product, value);
            } catch (IllegalAccessException e) {
                logger.warn(e.getMessage(), e);
            }
        }

        return (Product) product;
    }

    private List<Field> getAllAnnotatedField(
            Class<?> prodClass, Class<? extends Annotation> annotation, List<Field> fields) {

        Class<?> superClass = prodClass.getSuperclass();
        if ( prodClass == Object.class ) {
            return fields;

        } else {
            Field[] fieldsArray = Arrays.stream(prodClass.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(annotation))
                    .toArray(Field[]::new);

            fields.addAll(0, Arrays.asList(fieldsArray));
            return getAllAnnotatedField(superClass, annotation, fields);
        }

    }
}
