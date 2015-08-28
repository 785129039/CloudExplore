package com.sunsoft.study.algorithm;

import org.springframework.util.StringUtils;

/**
 * @File: Similarity.java
 * @Date: 2015��7��7��
 * @Author: wwei
 * @Copyright: ��Ȩ���� (C) 2015 ��ΰ����.
 * �㷨ϵ��: �����ַ��������ƶȣ���ֵԽС��Խ���ƣ�
 */
public class Similarity {

	public static void main(String[] args) {
		System.out.println(editDistance("�й���ţ�ƣ�sd", "���˺�������asdgsdfsdfdfgd"));
		System.out.println(computeDistance("�й���ţ�ƣ�sd", "���˺�������asdgsdfsdfdfgd"));
	}

	/**
	 * ʱ�临�Ӷ���O(n2)���ռ临�Ӷ���O(mn)
	 * @param source
	 * @param target
	 * @return
	 */
	public static Integer editDistance(String source, String target) {
		if (StringUtils.hasText(source) && StringUtils.hasText(target)) {
			int i, j;

			char[] sArray = source.toCharArray();
			char[] tArray = target.toCharArray();
			int sLen = sArray.length;
			int tLen = tArray.length;
			int[][] d = new int[sLen + 1][tLen + 1];
			for (i = 0; i <= sLen; i++)
				d[i][0] = i;
			for (j = 0; j <= tLen; j++)
				d[0][j] = j;

			for (i = 1; i <= sLen; i++) {
				for (j = 1; j <= tLen; j++) {
					if (sArray[i-1] == tArray[j-1]) {
						d[i][j] = d[i - 1][j - 1];
					} else {
						int ins = d[i][j - 1] + 1; // source�����ַ�
						int del = d[i - 1][j] + 1; // sourceɾ���ַ�
						int edi = d[i - 1][j - 1] + 1; // source�޸��ַ�
						d[i][j] = getMinNum(new Integer[] { ins, del, edi });
					}
				}
			}
			return d[sLen][tLen];
		}

		return null;
	}

	/**
	 * ʱ�临�Ӷ���3��n�η�����ʵ�ã�
	 * @param source
	 * @param target
	 * @return
	 */
	public static Integer computeDistance(String source, String target) {
		if(source != null && target != null) {
			char[] sArray = source.toCharArray();
			char[] tArray = target.toCharArray();
			if (sArray.length<=1 || tArray.length<=1)
				return Math.abs(sArray.length - tArray.length);
			if ((sArray[0] == tArray[0]))
				return editDistance(String.valueOf(sArray).substring(1), String.valueOf(tArray).substring(1));
			
			int ins = computeDistance(source,target.substring(1)) + 1; // source�����ַ�
			int del = computeDistance(source.substring(1),target) + 1; // sourceɾ���ַ�
			int edi = computeDistance(source.substring(1),target.substring(1)) + 1; // source�޸��ַ�
			return getMinNum(new Integer[] { ins, del, edi });
		}
		return null;
	}
	public static Integer getMinNum(Integer[] source) {
		if (source != null && source.length > 0) {
			int minnum = source[0];
			for (int i = 0; i < source.length; i++) {
				if (source[i] < minnum) {
					minnum = source[i];
				}
			}
			return minnum;
		}
		return null;
	}
}
