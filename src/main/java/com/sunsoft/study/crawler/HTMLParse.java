package com.sunsoft.study.crawler;

import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @File: HTMLParse.java
 * @Date: 2015��10��9��
 * @Author: wwei
 * @Copyright: ��Ȩ���� (C) 2015 
 *
 * @ע�⣺�����ݽ����ڱ���ʹ�ã���ֹ��й�Լ�������������ҵĿ��
 */
public class HTMLParse implements Parse {
	
	public void parse(InputStream input) {
		Document doc;
		try {
			doc = Jsoup.parse(input, "UTF-8", "");
			Element element = doc.getElementsByClass("x1h").get(0);
			Elements rows = element.getElementsByTag("tr");
			int size = rows != null ? rows.size() : 0;
			for (int i = 0; i < size; i++) {
				Element row = rows.get(i);
				Elements cols = i==0?row.getElementsByTag("th"):row.getElementsByTag("td");
				for(Element col : cols) {
					System.out.print(col.text() + "\t");
				}
				System.out.print("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
