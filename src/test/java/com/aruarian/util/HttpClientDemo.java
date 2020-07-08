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
	/*������վ*/
	@Test
	public void Test2() throws ClientProtocolException, IOException {
		//ʵ����HttpClient
		HttpClient httpclient = HttpClients.custom().build();
		String renRenLoginURL = "http://********.top/StudentServlet";//��¼�ĵ�ַ
		//���õķ���ΪPOST
		HttpPost httppost = new HttpPost(renRenLoginURL);
		//����NameValuePair���飬���ڴ洢�����͵Ĳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		//�����ʺ�
		nvps.add(new BasicNameValuePair("xuehao",""));
		//��������
		nvps.add(new BasicNameValuePair("xingming",""));
		nvps.add(new BasicNameValuePair("yanzheng",""));
		HttpResponse response = null;
		
		try {
			//�������ύ
			httppost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
			response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
//			httppost.abort();//�ͷ�����
		}
		System.out.println(response.getStatusLine());
		String entityString = EntityUtils.toString(response.getEntity(),"gbk");//ע�����
		System.out.println(entityString);
		//��¼��ɺ���Ҫ���������
		HttpGet httpget = new HttpGet("http://********.top/aruarian/select.jsp");
		//����ResponseHandler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		
		responseBody = httpclient.execute(httpget,responseHandler);
		
		httpget.abort();//�ͷ�����
		System.out.println(responseBody);//������󵽵�����
		
	}
	//���ύ
	public void Form(String w [],String id) throws ClientProtocolException, IOException {
		//ʵ����HttpClient
		HttpClient httpclient = HttpClients.custom().build();
		String renRenLoginURL = "http://**.***.***.**:8081/hnjdjw/cas/login.action";//��¼�ĵ�ַ
		//���õķ���ΪPOST
		HttpPost httppost = new HttpPost(renRenLoginURL);
		//����NameValuePair���飬���ڴ洢�����͵Ĳ���
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		//�����ʺ�
		nvps.add(new BasicNameValuePair("params",w[0]));
		nvps.add(new BasicNameValuePair("token",w[1]));
		nvps.add(new BasicNameValuePair("timestamp",w[2]));
//		//��������
//		nvps.add(new BasicNameValuePair("xingming","�ǿ���"));
//		nvps.add(new BasicNameValuePair("yanzheng","1231"));
		HttpResponse response = null;
		
		try {
			//�������ύ
			httppost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
			httppost.setHeader("Cookie", id);
			response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally {
//			httppost.abort();//�ͷ�����
		}
		System.out.println(response.getStatusLine());
		String entityString = EntityUtils.toString(response.getEntity(),"gbk");//ע�����
		System.out.println(entityString);
		//��¼��ɺ���Ҫ���������
		HttpGet httpget = new HttpGet("http://**.***.***.**:8081/hnjdjw/MainFrm.html");
		//�������ύ
		httpget.setHeader("Cookie", id);
		response = httpclient.execute(httpget);
		//����ResponseHandler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		
		responseBody = httpclient.execute(httpget,responseHandler);
		
		httpget.abort();//�ͷ�����
		System.out.println(responseBody);//������󵽵�����
		
	}
	/*ѧУ����ϵͳ*/
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
	
	
	/*����*/
	@Test
	public void Test4() throws ClientProtocolException, IOException {
		//ʵ����HttpClient
		HttpClient httpclient = HttpClients.custom().build();
		HttpGet httpget = new HttpGet("http://**.***.***.**:8081/hnjdjw/custom/js/SetKingoEncypt.jsp");
		//����ResponseHandler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		
		responseBody = httpclient.execute(httpget,responseHandler);
		
		httpget.abort();//�ͷ�����
		int a = responseBody.indexOf("'");
		int b = responseBody.indexOf("e = '");
//		System.out.println(responseBody.substring(a+1, a+20));
		System.out.println(responseBody.substring(b+5, b+24));
//		System.out.println(responseBody);//������󵽵�����
	}
	
	//����cookie
	@Test
	public void Test5() throws ClientProtocolException, IOException {
		HttpClient hc = HttpClients.custom().build(); 
		//��¼���棺http://**.***.***.**:8081/hnjdjw/cas/logon.action
		//����������ѧ����Ϣҳ��http://**.***.***.**:8081/hnjdjw/MainFrm.html
		//ͷ����Ϣ��http://**.***.***.**:8081/hnjdjw/frame14/Main_banner.jsp  ��ǰ�û�xxxͬѧ/xxx��ʦ
		//�ɼ�������http://**.***.***.**:8081/hnjdjw/student/xscj.stuckcj_data.jsp
		HttpPost httppost = new HttpPost("http://**.***.***.**:8081/hnjdjw/student/xscj.stuckcj_data.jsp");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		httppost.setHeader("Cookie", "JSESSIONID=xxxxxxxxxxxxxxxxxxxxxxx");//����cookie�Ҳ���ֵ
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
		httppost = new HttpPost("http://**.***.***.**:8081/hnjdjw/frame14/Main_banner.jsp");
		httppost.setHeader("Cookie", "JSESSIONID=xxxxxxxxxxxxxxxxxxxxxx");//����cookie�Ҳ���ֵ
		httppost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));	
		responseBody = hc.execute(httppost,responseHandler);
		httppost.abort();//�ͷ�����
//		responseBody = new sun.misc.BASE64Encoder().encode(responseBody.getBytes("GBK"));
//	        byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(responseBody);
//	        responseBody = new String(bytes, "UTF-8");
		System.out.println(responseBody);
	}
	
}

