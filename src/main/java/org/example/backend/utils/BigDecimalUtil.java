package org.example.backend.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

    // Chuyển đổi từ chuỗi sang BigDecimal
    public static BigDecimal fromString(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Chuỗi không được null hoặc rỗng");
        }
        return new BigDecimal(value);
    }

    // Chuyển đổi từ số sang BigDecimal
    public static BigDecimal fromNumber(Number value) {
        if (value == null) {
            throw new IllegalArgumentException("Giá trị không được null");
        }
        return BigDecimal.valueOf(value.doubleValue());
    }

    // Chuyển đổi từ BigDecimal sang chuỗi
    public static String toString(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Giá trị không được null");
        }
        return value.toString();
    }

    // Chuyển đổi từ BigDecimal sang số (Integer, Long, Double, v.v.)
    public static Number toNumber(BigDecimal value, Class<? extends Number> type) {
        if (value == null) {
            throw new IllegalArgumentException("Giá trị không được null");
        }
        if (type == Integer.class) {
            return value.intValue();
        } else if (type == Long.class) {
            return value.longValue();
        } else if (type == Double.class) {
            return value.doubleValue();
        } else if (type == Float.class) {
            return value.floatValue();
        }
        throw new IllegalArgumentException("Kiểu không hợp lệ: " + type.getName());
    }
//    BigDecimal valueFromString = BigDecimalUtil.fromString("123.45");
//    BigDecimal valueFromNumber = BigDecimalUtil.fromNumber(123);
//    String stringValue = BigDecimalUtil.toString(valueFromString);
//    Integer intValue = (Integer) BigDecimalUtil.toNumber(valueFromString, Integer.class);

}

