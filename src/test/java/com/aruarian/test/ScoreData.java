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
		//登录界面：http://**.***.***.**:8081/hnjdjw/cas/logon.action
		//机电教务个人学生信息页：http://**.***.***.**:8081/hnjdjw/MainFrm.html
		//头部信息：http://**.***.***.**:8081/hnjdjw/frame14/Main_banner.jsp  当前用户xxx同学/xxx老师
		//成绩总览：http://**.***.***.**:8081/hnjdjw/student/xscj.stuckcj_data.jsp
		HttpPost httppost = new HttpPost("http://**.***.***.**:8081/hnjdjw/student/xscj.stuckcj_data.jsp");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		httppost.setHeader("Cookie", cookie);//不带cookie找不到值
//		httppost.setHeader("Content-Length", "1124");
		//建立NameValuePair数组，用于存储欲传送的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		//输入参数    获取成绩的参数
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
		httppost.abort();//释放连接
		Message ms = new Message();
		System.out.println(ms.showList(responseBody));
	}
}
