package com.sunsoft.study.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookFacadeProxy implements InvocationHandler {

	private Object target;
	
	public Object bind(Object target) {
		this.target = target;
		 //ȡ�ô������  
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);   //Ҫ�󶨽ӿ�(����һ��ȱ�ݣ�cglib�ֲ�����һȱ��)  
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("��ʼ");
		Object result = method.invoke(target, args);
		System.out.println("����");
		
		return result;
	}

}
