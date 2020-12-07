package com.qihui.sun.util.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) throws ParseException {
        long nowDate = System.currentTimeMillis();
        System.out.println(nowDate);
        Date date2 = new Date(nowDate);
        System.out.println(date2);
        System.out.println("================");
        /*
            年 y
            月 M
            日 d
            时 H
            分 m
            秒 s
         */
        String pattern = "yyyy年MM月dd日HH时mm分ss秒";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        String formatDate = sdf.format(nowDate);
        System.out.println(formatDate);
        System.out.println("================");
        Date parseDate = sdf.parse(formatDate);
        System.out.println(parseDate);



    }

    @Test
    private void testTime () throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date parse = sdf.parse("2020-12-1 12:12:12");
        System.out.println(parse.getTime());
    }
}
