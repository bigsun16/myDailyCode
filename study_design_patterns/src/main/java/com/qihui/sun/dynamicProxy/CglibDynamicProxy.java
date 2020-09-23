package com.qihui.sun.dynamicProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibDynamicProxy {
	public static void main(String[] args) {
		TestCglibDynamicProxyBean bean = new TestCglibDynamicProxyBean();
		TestCglibDynamicProxyBean createProxy = (TestCglibDynamicProxyBean) Enhancer.create(TestCglibDynamicProxyBean.class, new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				if ("testMethod".equals(method.getName())) {
					return method.invoke(bean, args[0]+"_我是增强");
				} else {
					System.out.println("我是增强前");
					method.invoke(bean, args);
					System.out.println("我是增强后");
				}
				return null;
			}
		});
		
		String testMethod = createProxy.testMethod("aa");
		System.out.println(testMethod);
		createProxy.testVoid("bb");
	}
}
