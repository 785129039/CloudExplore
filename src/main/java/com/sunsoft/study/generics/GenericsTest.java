package com.sunsoft.study.generics;

import org.junit.Test;

import com.sunsoft.study.generics.provider.FooService;
import com.sunsoft.study.generics.provider.FooServiceProvider;
import com.sunsoft.study.generics.provider.Service;
import com.sunsoft.study.generics.provider.ServiceProvider;
import com.sunsoft.study.generics.provider.ServiceProviderRegistry;

/**
 * @File: GenericsTest.java
 * @Date: 2015��10��27��
 * @Author: wwei
 * @Copyright: ��Ȩ���� (C) 2015 
 *
 * @ע�⣺�����ݽ����ڱ���ʹ�ã���ֹ��й�Լ�������������ҵĿ��
 */
public class GenericsTest {
	
     @Test
     public void ServiceProviderTest() {
    	 ServiceProviderRegistry<? extends Service> registry = new ServiceProviderRegistry<Service>();
         registry.register(FooService.class, new FooServiceProvider());
         ServiceProvider<FooService> provider = registry.lookup(FooService.class);
         // ������
         provider.getService().product();;
     }
}
