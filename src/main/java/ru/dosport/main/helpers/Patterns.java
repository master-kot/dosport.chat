package ru.dosport.main.helpers;

/**
 * Паттерны форматирования и методы работы с ними
 */
public class Patterns {

    private Patterns() {
    }

    public static final String LOCAL_DATE_PATTERN = "yyyy-MM-dd";
    public static final String LOCAL_DATE_TYPE = "LocalDate";
    public static final String LOCAL_DATE_EXAMPLE_1 = "2021-03-25";

    public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String LOCAL_DATE_TIME_TYPE = "LocalDateTime";
    public static final String LOCAL_DATE_TIME_EXAMPLE_1 = "2021-03-23 11:22";
    public static final String LOCAL_DATE_TIME_EXAMPLE_2 = "2021-05-25 22:33";

    public static final String LOCAL_TIME_PATTERN = "HH:mm";
    public static final String LOCAL_TIME_TYPE = "LocalTime";
    public static final String LOCAL_TIME_EXAMPLE_1 = "11:22";
    public static final String LOCAL_TIME_EXAMPLE_2 = "22:33";

    public static final String EVENT_SORT_BY_DATE = "creationDateTime";
    public static final int PAGE_SIZE = 15;

}
