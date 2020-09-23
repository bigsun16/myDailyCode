package com.qihui.sun.util.date;

import java.util.Calendar;
import java.util.Date;

public class TestCalendar {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        System.out.println("----------------------");
        Date time = calendar.getTime();
        System.out.println(time);
        System.out.println("----------------------");
        calendar.set(Calendar.YEAR,8888);
        calendar.set(Calendar.MONTH,8);
        calendar.set(Calendar.DATE,8);
        calendar.set(9999,9,9);
        System.out.println("----------------------");
        calendar.add(Calendar.YEAR, 2);
        calendar.add(Calendar.MONTH, -2);
        System.out.println("----------------------");
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));//西方的月份是0-11，东方是1-12
        System.out.println(calendar.get(Calendar.DATE));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

        System.out.println("----------------------");
    }
}
