package com.qihui.sun.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.qihui.sun.service.UserService;
import com.qihui.sun.service.UserServiceImpl;

public class JdkDynamicProxy {
	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		
		UserService newProxyInstance = (UserService)Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(), UserServiceImpl.class.getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				String name = method.getName();
				if ("show".equals(name)) {
					System.out.println("代理前做点事情");
					method.invoke(userService, args);
					System.out.println("代理后做点事情");
					return null;
				} 
				return method.invoke(userService, args[0]+" world");
			}
		});
		newProxyInstance.show();
		String testReturn = newProxyInstance.testReturn("hello");
		System.out.println("-------"+testReturn);
	}
}
