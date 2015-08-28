package com.sunsoft.study.pattern.command;

/**
 * �����ߣ�˾��Ա��
 * @File: Invoker.java
 * @Date: 2015��5��13��
 * @Author: wwei
 * @Copyright: ��Ȩ���� (C) 2015 �й��ƶ� �����з�����.
 *
 * @ע�⣺�����ݽ������й��ƶ��ڲ����ģ���ֹ��й�Լ�������������ҵĿ��
 */
public class Invoker {
	private Command command;
		
	@SuppressWarnings("unused")
	private Invoker() {}
	
	public Invoker(Command command) {
		this.command = command;
	}
	
	public void action() {
		command.exe();
	}
}
