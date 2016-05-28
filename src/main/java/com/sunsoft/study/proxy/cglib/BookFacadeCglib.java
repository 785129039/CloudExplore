package com.sunsoft.study.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class BookFacadeCglib implements MethodInterceptor {

	private Object target;
	
	public Object getInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("��ʼ");
		Object result = method.invoke(this.target, args);
		System.out.println(result);
		// ���߽Կ�  result = proxy.invoke(this.target, args);
		System.out.println("����");
		return result;
	}

}
