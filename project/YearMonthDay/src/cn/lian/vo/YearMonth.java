package cn.lian.vo;

/**
 * @author 张英琪
 */
public class YearMonth {
    private int year;
    private int month;
    public YearMonth(){
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public YearMonth(int year, int month) {
        this.year = year;
        this.month = month;
    }
}
