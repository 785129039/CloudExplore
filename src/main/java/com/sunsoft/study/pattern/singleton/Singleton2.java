package com.sunsoft.study.pattern.singleton;

/**
 * ��synchronized�ؼ��ּ������ڲ���Ҳ����˵�����õ�ʱ���ǲ���Ҫ�����ģ�ֻ����instanceΪnull�������������ʱ�����Ҫ������
 * ������һ�������������ǣ�����������������п���������ģ���������������Javaָ���д�������͸�ֵ�����Ƿֿ����еģ�Ҳ����˵instance = new
 * Singleton();����Ƿ�����ִ�еġ�����JVM������֤�������������Ⱥ�˳��Ҳ����˵�п���JVM��Ϊ�µ�Singletonʵ������ռ䣬
 * Ȼ��ֱ�Ӹ�ֵ��instance��Ա��Ȼ����ȥ��ʼ�����Singletonʵ���������Ϳ��ܳ����ˣ�������A��B�����߳�Ϊ����
 * a>A��B�߳�ͬʱ�����˵�һ��if�ж� b>A���Ƚ���synchronized�飬����instanceΪnull��������ִ��instance = new
 * Singleton(); c>����JVM�ڲ����Ż����ƣ�JVM�Ȼ�����һЩ�����Singletonʵ���Ŀհ��ڴ棬����ֵ��instance��Ա��
 * ע���ʱJVMû�п�ʼ��ʼ�����ʵ������Ȼ��A�뿪��synchronized�顣
 * d>B����synchronized�飬����instance��ʱ����null������������뿪��synchronized�鲢��������ظ����ø÷����ĳ���
 * e>��ʱB�̴߳���ʹ��Singletonʵ����ȴ������û�б���ʼ�������Ǵ������ˡ�
 * ���Գ������п��ܷ���������ʵ���������й����Ǻܸ��ӵģ���������ǾͿ��Կ�������������д���̻߳����µĳ�������Ѷȣ�����ս�ԡ����ǶԸó�������һ���Ż���
 * ��singleton2
 */
public class Singleton2 {  
  
    /* ˽�й��췽������ֹ��ʵ���� */  
    private Singleton2() {  
    }  
  
    /* �˴�ʹ��һ���ڲ�����ά������ */  
    private static class SingletonFactory {  
        private static Singleton2 instance = new Singleton2();  
    }  
  
    /* ��ȡʵ�� */  
    public static Singleton2 getInstance() {  
        return SingletonFactory.instance;  
    }  
  
    /* ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� */  
    public Object readResolve() {  
        return getInstance();  
    }
    
    public static void main(String[] args) {
    	System.out.println(getInstance() == getInstance());
	}
}  