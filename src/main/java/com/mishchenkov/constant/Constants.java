package com.mishchenkov.constant;

import java.util.Comparator;

public final class Constants {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    // Errors
    public static final String ERR_MSG_IT_DOES_NOT_HAVE_AN_INDEX = "system doesn't have some index";
    public static final String ERR_MSG_IT_CONTAINS_NOT_NUMBERS = "the input line contains one or more not number symbol";
    public static final String ERR_MSG_CART_IS_EMPTY = "Cart is empty";
    public static final String ERR_MSG_IT_DOES_NOT_HAVE_CLOSE_ORDER = "the shop doesn't have close order";
    public static final String ERR_MSG_LOOP = "You made a mistake when are typing a command. Try your attempt";
    public static final String ERR_MSG_CANT_SAVE_FILE = "System can't save Products to the file.";

    // Props
    public static final String PRODUCT_DB_FILE = "./product.db.obj";

    // Creator titles
    public static final String PROD_NAME_MSG = "enter product name: ";
    public static final String PROD_SKU_MSG = "enter product sku: ";
    public static final String PROD_DESCRIPTION_MSG = "enter product description: ";
    public static final String PROD_STATE_MSG = "enter product state (true OR false): ";
    public static final String PROD_PRICE_MSG = "enter product perice as it shows as example (1.23): ";

    public static final String PROD_DIAMETER_MSG = "enter lamp diameter: ";
    public static final String PROD_HEIGHT_MSG = "enter lamp height: ";
    public static final String PROD_POWER_MSG = "enter lamp power: ";

    public static final String PROD_SPIRAL_MSG = "enter spiral count: ";
    public static final String PROD_TWINKLE_MSG = "enter twinkle mod (true OR false): ";

    public static final String PROD_LED_COUNT_MSG = "enter led count: ";
    public static final String PROD_RADIATOR_MSG = "enter radiator mod (true OR false): ";

    public static final String PROD_BLUETOOTH_MSG = "enter bluetooth mod (true OR false): ";
    public static final String PROD_WIFI_MSG = "enter wifi mod (true OR false): ";

    //  Regexp
    public static final String REGEXP_MATCH_JUST_ONE_AND_ZERO = "[0|1]";
    public static final String REGEXP_MATCH_NUMBERS = "^[\\d]+$";
    public static final String REGEXP_MATCH_NUMBERS_WITH_GAPS = "^([\\d| ]+)$";
    public static final String REGEXP_MATCH_DATE = "^(\\d{4}):([0|1]\\d){1,2}:(\\d{1,2})$";
    public static final String REGEXP_MATCH_FLOAT = "^[\\d]+\\.[\\d]{1,2}$";
    public static final String REGEXP_MATCH_BOOLEAN = "^(true|false)$";
    public static final String REGEXP_MATCH_STRING = "[\\d\\D]+";

    public static final String REGEXP_MATCH_TCP_GET_COUNT = "^(get count)$";
    public static final String REGEXP_MATCH_TCP_GET_ITEM = "^(get item=[\\d]+)$";
    public static final String REGEXP_MATCH_NOT_GET_ITEM_OR_COUNT = "^((?!get count|get item)[\\w\\W]*)";

    public static final String REGEXP_MATCH_GET_SHOP_REQUEST = "^((GET /shop/).*)";
    public static final String REGEXP_MATCH_GET_SHOP_COUNT = ".*( /shop/count ).*";
    public static final String REGEXP_MATCH_GET_SHOP_ITEM = ".*( /shop/item\\?get_info=[\\w]+ ).*";

    // Menu
    public static final String WELCOME_MSG_FOR_PROD_GENERATION_TYPE
            = "Enter method for generation new products (0 - manual way, 1 - random way): ";
    public static final String WELCOME_MSG_LANGUAGE_INDEX = "Enter language index (0 or more _): ";

    // Comparator
    public static final Comparator<Class<?>> CLASS_COMPARATOR =
            Comparator.comparing(Class::getSimpleName, String::compareTo);

    // Server props
    public static final String SERVER_HOST = "localhost";
    public static final int TCP_SERVER_PORT = 3000;
    public static final int HTTP_SERVER_PORT = 80;

    private Constants() {}
}
