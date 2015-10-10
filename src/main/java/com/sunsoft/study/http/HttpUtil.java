package com.sunsoft.study.http;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;

/**
 * @File: HttpUtil.java
 * @Date: 2015��9��22��
 * @Author: wwei
 * @Copyright: ��Ȩ���� (C) 2015 
 *
 * @ע�⣺�����ݽ����ڱ���ʹ�ã���ֹ��й�Լ�������������ҵĿ��
 */
public class HttpUtil {
	
	public static JSONObject  send(){
		
		RequestObject obj = init("http://112.33.1.160:81/healthcare_service_restful/s/userService/getUserByUid");
		
		HttpResponse response = RequestManager.getInstance().sendRequest(obj);
		String responseMsg = RequestManager.parseResponse(response);
		JSONObject json=JSONObject.fromObject(responseMsg);
		
		return json;
	}
	
	 /**
	  * @param host
	  * @param port
	  * @param path
	  * @param vo
	  * @return
	  */
	private static RequestObject init(String path) {
		RequestObject obj = new RequestObject();
		obj.setRequestType(RequestType.POST);

		obj.setPath(path);
		obj.setEncoding(RequestConstants.ENCODING_UTF_8);
		obj.putParam("invokeKey", "[HAIJK-S]nHtIktPPQM")
				.putParam("appKey", "HAIJK").putParam("uid", "wanggang");

		
		return obj;
	}

	public static void main(String[] args) {
		JSONObject object = send();
		System.out.println(object.toString());
	}
}
