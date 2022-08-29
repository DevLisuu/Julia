package me.devlisuu.julia.util;

public class TimeFormater {
    private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;

    // Credits: http://www.java2s.com/example/java-utility-method/time-format/formatuptime-long-uptime-2b953.html
    public static String formatTime(long time) {
        StringBuilder buf = new StringBuilder();
        if (time > DAY) {
            long days = (time - time % DAY) / DAY;
            buf.append(days);
            buf.append(" Days");
            time = time % DAY;
        }
        if (time > HOUR) {
            long hours = (time - time % HOUR) / HOUR;
            if (buf.length() > 0) {
                buf.append(", ");
            }
            buf.append(hours);
            buf.append(" Hours");
            time = time % HOUR;
        }
        if (time > MINUTE) {
            long minutes = (time - time % MINUTE) / MINUTE;
            if (buf.length() > 0) {
                buf.append(", ");
            }
            buf.append(minutes);
            buf.append(" Minutes");
            time = time % MINUTE;
        }
        if (time > SECOND) {
            long seconds = (time - time % SECOND) / SECOND;
            if (buf.length() > 0) {
                buf.append(", ");
            }
            buf.append(seconds);
            buf.append(" Seconds");
            // time = time % SECOND;
        }

        return buf.toString();
    }
}