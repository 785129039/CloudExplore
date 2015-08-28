package com.sunsoft.study.pattern.singleton;

/**
 * ���ǣ�synchronized�ؼ�����ס������������������÷����������ϻ������½�����Ϊÿ�ε���getInstance()����Ҫ�Զ�����������ʵ�ϣ�
 * ֻ���ڵ�һ�δ��������ʱ����Ҫ������֮��Ͳ���Ҫ�ˣ����ԣ�����ط���Ҫ�Ľ���
 */
public class Singleton1 {

	/* ����˽�о�̬ʵ������ֹ�����ã��˴���ֵΪnull��Ŀ����ʵ���ӳټ��� */
	private static Singleton1 instance = null;

	/* ˽�й��췽������ֹ��ʵ���� */
	private Singleton1() {
	}

	/* ��̬���̷���������ʵ�� */
	public static Singleton1 getInstance() {
		if (instance == null) {
			synchronized (instance) {
				if (instance == null) {
					instance = new Singleton1();
				}
			}
		}
		return instance;
	}

	/* ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� */
	public Object readResolve() {
		return instance;
	}
}