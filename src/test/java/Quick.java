import org.junit.Test;


/**
 * @File: Quick.java
 * @Date: 2016��1��26��
 * @Author: wwei
 * @Copyright: ��Ȩ���� (C) 2015 
 * ����
 * @ע�⣺�����ݽ����ڱ���ʹ�ã���ֹ��й�Լ�������������ҵĿ��
 */
public class Quick {
	public void sort(int arr[], int low, int high) {
		int l = low;
		int h = high;
		int povit = arr[low];
		while(l<h) {
			while(l<h && arr[h] >= povit) h--;
			if(l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				l ++;
			}
			while(l<h && arr[l] <= povit) l++;
			if(l < h) {
				int temp = arr[h];
				arr[h] = arr[l];
				arr[l] = temp;
				h --;
			}
		}
		System.out.print("l="+(l+1)+"h="+(h+1)+"povit="+povit+"\n");
	}
	
	@Test
	public void test() {
		int arr[]={23,123,45,324,12,3,452,1,4847,23,234,121,676,189,7876};
		sort(arr, 0, 14);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+",");
		}
	}
}