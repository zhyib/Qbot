package qbot.util;

import java.util.Calendar;

public class TimeSet {
    private final String year;
    private final String month;
    private final String day;
    private final String hour;
    private final String minute;
    private final String description;


    public TimeSet(String year, String month, String day, String hour, String minute, String description) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.description = description;
    }

    public boolean equalsCal(Calendar calendar) {
        return calendar.get(Calendar.YEAR) == Integer.parseInt(this.year)
                && calendar.get(Calendar.MONTH) == Integer.parseInt(this.month) - 1
                && calendar.get(Calendar.DATE) == Integer.parseInt(this.day)
                && calendar.get(Calendar.HOUR_OF_DAY) == Integer.parseInt(this.hour)
                && calendar.get(Calendar.MINUTE) == Integer.parseInt(this.minute);
    }

    public String getLine() {
        return year + "年" + month + "月" + day + "日 " + hour + ":" + minute + " " + description;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "TimeSet{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                ", minute='" + minute + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
