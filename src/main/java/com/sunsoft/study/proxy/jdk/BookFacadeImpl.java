package com.sunsoft.study.proxy.jdk;

public class BookFacadeImpl implements BookFacade,Book {

	public void addBook() {
		System.out.println("����ͼ�鷽��������");  
	}

	public void read() {
		System.out.println("�Ķ�ͼ�鷽��������");  
	}

}
