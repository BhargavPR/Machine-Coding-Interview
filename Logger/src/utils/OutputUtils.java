package utils;

import model.configuration.LogLevel;
import model.log.Log;

public class OutputUtils {

    public static void printLog(Log log, String dateTimeFormat) {
        printLog(log.getMessage(), log.getTimestamp(), log.getLogLevel(), dateTimeFormat);
    }

    public static void printLog(String message, Long timestamp, LogLevel logLevel, String dateTimeFormat) {
        String time = CalendarUtils.getDateTimeString(timestamp, dateTimeFormat);
        System.out.println(time + " [" + logLevel.toString() + "] " + message);
    }

}
