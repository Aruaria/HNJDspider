package com.aruarian.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class ScoreData {
	public void showData(String cookie) throws ClientProtocolException, IOException {
		HttpClient hc = HttpClients.custom().build(); 
		//��¼���棺http://**.***.***.**:8081/hnjdjw/cas/logon.action
		//����������ѧ����Ϣҳ��http://**.***.***.**:8081/hnjdjw/MainFrm.html
		//ͷ����Ϣ��http://**.***.***.**:8081/hnjdjw/frame14/Main_banner.jsp  ��ǰ�û�xxxͬѧ/xxx��ʦ
		//�ɼ�������http://**.***.***.**:8081/hnjdjw/student/xscj.stuckcj_data.jsp
		HttpPost httppost = new HttpPost("http://**.***.***.**:8081/hnjdjw/student/xscj.stuckcj_data.jsp");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		httppost.setHeader("Cookie", cookie);//����cookie�Ҳ���ֵ
//		httppost.setHeader("Content-Length", "1124");
		//����NameValuePair���飬���ڴ洢�����͵Ĳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		//�������    ��ȡ�ɼ��Ĳ���
		nvps.add(new BasicNameValuePair("sjxz","sjxz1"));
		nvps.add(new BasicNameValuePair("ysyx","yxcj"));
		nvps.add(new BasicNameValuePair("zx","1"));
		nvps.add(new BasicNameValuePair("fx","1"));
		nvps.add(new BasicNameValuePair("xn1","2020"));
		nvps.add(new BasicNameValuePair("ysyxS","on"));
		nvps.add(new BasicNameValuePair("sjxzS","on"));
		nvps.add(new BasicNameValuePair("zxC","on"));
		nvps.add(new BasicNameValuePair("fxC","on"));
		nvps.add(new BasicNameValuePair("menucode_current","403"));
		httppost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));	
		String responseBody = "";
		responseBody = hc.execute(httppost,responseHandler);
		httppost.abort();//�ͷ�����
		Message ms = new Message();
		System.out.println(ms.showList(responseBody));
	}
}
