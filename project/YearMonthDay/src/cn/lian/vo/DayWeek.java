package cn.lian.vo;

/**
 * @author 张英琪
 */
public class DayWeek {
    private int week;
    private int day;

    public DayWeek() {
    }

    public DayWeek(int week, int day) {
        this.week = week;
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
