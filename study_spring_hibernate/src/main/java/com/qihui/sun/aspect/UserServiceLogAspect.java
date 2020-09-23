package com.qihui.sun.aspect;

import org.aspectj.lang.JoinPoint;

public class UserServiceLogAspect {

	public void saveLogBefore(JoinPoint joinPoint) {
		System.out.println("前置通知。。。"+joinPoint.getTarget()+"------"+joinPoint.getSignature());
	}
	public void saveLogAfter(JoinPoint joinPoint) {
		System.out.println("后置通知..."+joinPoint.getClass()+"--------"+joinPoint.getArgs());
	}
}
