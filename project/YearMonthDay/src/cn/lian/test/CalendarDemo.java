package cn.lian.test;

import sun.util.resources.ar.CalendarData_ar;

import java.util.Calendar;



/**
 * @author 张英琪
 */
public class CalendarDemo {
    public static void main(String[] args) {
        Calendar  c = Calendar.getInstance();
        System.out.println("余数测试"+0%7);

        System.out.println("========当前日期=============");
        printDate(c);

        //自定义设置日期
        c.set(Calendar.YEAR,2025);
        c.set(Calendar.MONTH,1);
        c.set(Calendar.DATE,1);
        System.out.println("========自定义日期=============");
        printDate(c);

        //roll方法的使用，roll方法只是在相应时间属性的域内滚动
        //月的时间属性1-12,日的时间属性1-31
//        c.roll(Calendar.MONTH,-1);
//        System.out.println("=============后退一个月=========");
//        printDate(c);
        System.out.println("=============后退一天=========");
        c.roll(Calendar.DATE,-1);
        printDate(c);
        int days = c.get(Calendar.DATE);

    }
    public static void printDate(Calendar c){
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);//month是从0开始计算的
        int date = c.get(Calendar.DATE);
        System.out.println("year= "+year+" month = "+(month+1)+" date  = "+date);
    }
}
