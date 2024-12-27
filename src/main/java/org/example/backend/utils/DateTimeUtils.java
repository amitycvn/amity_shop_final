package org.example.backend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {

    // Định dạng mặc định cho String to Instant và ngược lại
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_PATTERN).withZone(ZoneId.of("UTC"));

    // Chuyển từ String sang Instant
    public static Instant stringToInstant(String dateString) {
        return Instant.parse(dateString);
    }

    // Chuyển từ Instant sang String
    public static String instantToString(Instant instant) {
        return FORMATTER.format(instant);
    }

    // Chuyển từ Date sang Instant
    public static Instant dateToInstant(Date date) {
        return date.toInstant();
    }

    // Chuyển từ Instant sang Date
    public static Date instantToDate(Instant instant) {
        return Date.from(instant);
    }

    // Chuyển từ String sang Date với định dạng tùy chỉnh
    public static Date stringToDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateString);
    }

    // Chuyển từ Date sang String với định dạng tùy chỉnh
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    // Chuyển từ String sang Date với định dạng mặc định
    public static Date stringToDate(String dateString) throws ParseException {
        return stringToDate(dateString, DEFAULT_PATTERN);
    }

    // Chuyển từ Date sang String với định dạng mặc định
    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_PATTERN);
    }

    // Chuyển từ String sang LocalDateTime
    public static LocalDateTime stringToLocalDateTime(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateString, formatter);
    }

    // Chuyển từ LocalDateTime sang String
    public static String localDateTimeToString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }
}
