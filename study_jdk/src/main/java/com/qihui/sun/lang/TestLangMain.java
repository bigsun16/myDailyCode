package com.qihui.sun.lang;

public class TestLangMain {

	public static void main(String[] args) {
	}

	public static void testString(Long num, Long total) {
		String value = String.format("%.2f", ((num.doubleValue() / total.doubleValue()) * 100));
        System.out.println(value);
		double double1 = Double.parseDouble(value);
		System.out.println(Math.floor(double1));
		System.out.println(total / num);
	}

}
