package utils;

public class SlotUtils {

    public static Integer getStartTime(String time) {
        String[] timings = time.split("-");
        return getTime(timings[0]);
    }

    public static Integer getEndTime(String time) {
        String[] timings = time.split("-");
        return getTime(timings[1]);
    }

    public static Integer getTime(String time) {
        String hour = time.split(":")[0];
        String minute = time.split(":")[1];
        return (((hour.charAt(0) - '0') * 10 + (hour.charAt(1) - '0')) * 60) +
                ((minute.charAt(0) - '0') * 10 + (minute.charAt(1) - '0'));
    }

    public static String getTimeString(Integer time) {
        int hour = time / 60;
        int minute = time % 60;
        String hourString = hour < 10 ? ("0" + hour) : Integer.toString(hour);
        String minuteString = minute < 10 ? ("0" + minute) : Integer.toString(minute);
        return hourString + ":" + minuteString;

    }

}
