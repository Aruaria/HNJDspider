package com.aruarian.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClientDemo {
	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	public void Test1() throws ClientProtocolException, IOException {
		String cookie= "JSESSIONID="+this.GetSessionId();
	}
	/*个人网站*/
	@Test
	public void Test2() throws ClientProtocolException, IOException {
		//实例化HttpClient
		HttpClient httpclient = HttpClients.custom().build();
		String renRenLoginURL = "http://********.top/StudentServlet";//登录的地址
		//采用的方法为POST
		HttpPost httppost = new HttpPost(renRenLoginURL);
		//建立NameValuePair数组，用于存储欲传送的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		//输入帐号
		nvps.add(new BasicNameValuePair("xuehao",""));
		//输入密码
		nvps.add(new BasicNameValuePair("xingming",""));
		nvps.add(new BasicNameValuePair("yanzheng",""));
		HttpResponse response = null;
		
		try {
			//表单参数提交
			httppost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
			response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
//			httppost.abort();//释放连接
		}
		System.out.println(response.getStatusLine());
		String entityString = EntityUtils.toString(response.getEntity(),"gbk");//注意编码
		System.out.println(entityString);
		//登录完成后需要的请求界面
		HttpGet httpget = new HttpGet("http://********.top/aruarian/select.jsp");
		//构建ResponseHandler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		
		responseBody = httpclient.execute(httpget,responseHandler);
		
		httpget.abort();//释放连接
		System.out.println(responseBody);//输出请求到的内容
		
	}
	//表单提交
	public void Form(String w [],String id) throws ClientProtocolException, IOException {
		//实例化HttpClient
		HttpClient httpclient = HttpClients.custom().build();
		String renRenLoginURL = "http://**.***.***.**:8081/hnjdjw/cas/login.action";//登录的地址
		//采用的方法为POST
		HttpPost httppost = new HttpPost(renRenLoginURL);
		//建立NameValuePair数组，用于存储欲传送的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		//输入帐号
		nvps.add(new BasicNameValuePair("params",w[0]));
		nvps.add(new BasicNameValuePair("token",w[1]));
		nvps.add(new BasicNameValuePair("timestamp",w[2]));
//		//输入密码
//		nvps.add(new BasicNameValuePair("xingming","乔俊鹏"));
//		nvps.add(new BasicNameValuePair("yanzheng","1231"));
		HttpResponse response = null;
		
		try {
			//表单参数提交
			httppost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
			httppost.setHeader("Cookie", id);
			response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
//			httppost.abort();//释放连接
		}
		System.out.println(response.getStatusLine());
		String entityString = EntityUtils.toString(response.getEntity(),"gbk");//注意编码
		System.out.println(entityString);
		//登录完成后需要的请求界面
		HttpGet httpget = new HttpGet("http://**.***.***.**:8081/hnjdjw/MainFrm.html");
		//表单参数提交
		httpget.setHeader("Cookie", id);
		response = httpclient.execute(httpget);
		//构建ResponseHandler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		
		responseBody = httpclient.execute(httpget,responseHandler);
		
		httpget.abort();//释放连接
		System.out.println(responseBody);//输出请求到的内容
		
	}
	/*学校教务系统*/
	public String GetSessionId() throws ClientProtocolException, IOException {
			 CloseableHttpClient httpClient = HttpClients.createDefault();
		     HttpGet get=new HttpGet("http://**.***.***.**:8081/hnjdjw/cas/login.action");
		     HttpClientContext context = HttpClientContext.create();
	         CloseableHttpResponse response = httpClient.execute(get, context);
//	     System.out.println(">>>>>>headers:");
//	            Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
//            System.out.println(">>>>>>cookies:");
//            context.getCookieStore().getCookies().forEach(System.out::println);
//            Iterator it = context.getCookieStore().getCookies().iterator();
//            while(it.hasNext()) {
//            	System.out.println("---------------------------------------------------------------------------------------------------------");
//            	System.out.println(it.next());
//            }
            ArrayList<Cookie> ck = (ArrayList<Cookie>) context.getCookieStore().getCookies();
            String a = ck.get(0).toString();
            int b = a.indexOf("ue:");
            return a.substring(b+4,b+36);
	}
	
	
	/*加密*/
	@Test
	public void Test4() throws ClientProtocolException, IOException {
		//实例化HttpClient
		HttpClient httpclient = HttpClients.custom().build();
		HttpGet httpget = new HttpGet("http://**.***.***.**:8081/hnjdjw/custom/js/SetKingoEncypt.jsp");
		//构建ResponseHandler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		
		responseBody = httpclient.execute(httpget,responseHandler);
		
		httpget.abort();//释放连接
		int a = responseBody.indexOf("'");
		int b = responseBody.indexOf("e = '");
//		System.out.println(responseBody.substring(a+1, a+20));
		System.out.println(responseBody.substring(b+5, b+24));
//		System.out.println(responseBody);//输出请求到的内容
	}
	
	//设置cookie
	@Test
	public void Test5() throws ClientProtocolException, IOException {
		HttpClient hc = HttpClients.custom().build(); 
		//登录界面：http://**.***.***.**:8081/hnjdjw/cas/logon.action
		//机电教务个人学生信息页：http://**.***.***.**:8081/hnjdjw/MainFrm.html
		//头部信息：http://**.***.***.**:8081/hnjdjw/frame14/Main_banner.jsp  当前用户xxx同学/xxx老师
		//成绩总览：http://**.***.***.**:8081/hnjdjw/student/xscj.stuckcj_data.jsp
		HttpPost httppost = new HttpPost("http://**.***.***.**:8081/hnjdjw/student/xscj.stuckcj_data.jsp");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		httppost.setHeader("Cookie", "JSESSIONID=xxxxxxxxxxxxxxxxxxxxxxx");//不带cookie找不到值
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
		httppost = new HttpPost("http://**.***.***.**:8081/hnjdjw/frame14/Main_banner.jsp");
		httppost.setHeader("Cookie", "JSESSIONID=xxxxxxxxxxxxxxxxxxxxxx");//不带cookie找不到值
		httppost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));	
		responseBody = hc.execute(httppost,responseHandler);
		httppost.abort();//释放连接
//		responseBody = new sun.misc.BASE64Encoder().encode(responseBody.getBytes("GBK"));
//	        byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(responseBody);
//	        responseBody = new String(bytes, "UTF-8");
		System.out.println(responseBody);
	}
	
}

