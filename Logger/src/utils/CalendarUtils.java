package utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CalendarUtils {

    public static String getDateTimeString(Long timeInMilliseconds, String format) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMilliseconds), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

}
