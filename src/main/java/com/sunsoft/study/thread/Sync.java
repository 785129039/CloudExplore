package com.sunsoft.study.thread;

/**
 * 
 * @File: Sync.java
 * @Date: 2015��7��22��
 * @Author: wwei
 * @Copyright: ��Ȩ���� (C) 2015 ��ΰ����.
 * synchronized��ס���Ǵ��뻹�Ƕ���
 */
class Sync {
	/**
	 * ��ס���ǵ�����������Ķ���
	 */
	public synchronized void test1() {
		System.out.println("test��ʼ..");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test����..");
	}

	/**
	 * ��ס���ǵ���synchronized�����еĶ���
	 */
	public void test2() {
		synchronized (this) {
			System.out.println("test��ʼ..");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("test����..");
		}
	}
	
	/**
	 * ��ס����������Ӧ��Class����
	 */
	public void test3() {
		synchronized (Sync.class) {
			System.out.println("test��ʼ..");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("test����..");
		}
	}
	
	public void test4() {
		synchronized (Sync.class) {
			System.out.println("runnging...processss");
		};
	}
}