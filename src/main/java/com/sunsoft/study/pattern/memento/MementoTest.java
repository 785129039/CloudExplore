package com.sunsoft.study.pattern.memento;

import org.junit.Test;

public class MementoTest {
	
	@Test
	public void test() {
		Original original = new Original("egg");
		Storage storage = new Storage(original.createMemento());
		// �޸�ԭʼ���״̬  
        System.out.println("��ʼ��״̬Ϊ��" + original.getValue());  
        original.setValue("niu");  
        System.out.println("�޸ĺ��״̬Ϊ��" + original.getValue());  
        
        // �ظ�ԭʼ���״̬  
        original.restoreMemento(storage.getMemento());  
        System.out.println("�ָ����״̬Ϊ��" + original.getValue());  
	}
}
