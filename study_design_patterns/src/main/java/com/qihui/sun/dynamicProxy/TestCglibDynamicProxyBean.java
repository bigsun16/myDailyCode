package com.qihui.sun.dynamicProxy;

public class TestCglibDynamicProxyBean {
	public String testMethod(String aa) {
		return aa;
	}
	public void testVoid(String aa) {
		System.out.println(aa);
	}
}
