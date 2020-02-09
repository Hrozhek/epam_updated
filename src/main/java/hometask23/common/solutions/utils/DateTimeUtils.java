package hometask23.common.solutions.utils;

import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private DateTimeUtils(){}
    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }
    public static DateTimeFormatter getDateFormatter(String format) {
        return DateTimeFormatter.ofPattern(format);
    }
}
