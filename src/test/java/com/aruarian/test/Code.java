package com.aruarian.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Base64;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class Code {
	/**
	 * @author aruarian
	 * @param code ��֤��
	 * @param sessionId 
	 * @param uId ѧ��
	 * @param uPassword ����
	 * @param return ������
	 * ģ����ܱ��룬���ؽ��
	 */
	public String[] getCode(String code,String cookie,String uId,String uPassword) throws ClientProtocolException, IOException {
		String params = "";
		String p_username = "_u"+code;
		String p_password = "_p"+code;
		String sessionId = cookie.substring(11);
		String userName = BaseChange(uId,sessionId); //������ܴ���
		
		String password = DigestUtils.md5Hex(DigestUtils.md5Hex(uPassword)+DigestUtils.md5Hex(code));//MD5��������
		//���л�ϼ���  �û��� ���� ��֤��  �̶���*���ڵ�����Ĺ���
		params = p_username+"="+userName+"&"+p_password+"="+password+"&randnumber="+code+"&isPasswordPolicy="+"0"+
	             "&txt_mm_expression="+"8"+"&txt_mm_length="+"11"+"&txt_mm_userzh="+"1";
		//����ϵͳ�����Կ��ϵͳʱ���
		String keyAndTime [] = GetKey(cookie);
		String _deskey = keyAndTime[0];
		String _nowtime = keyAndTime[1];
		String token = DigestUtils.md5Hex(DigestUtils.md5Hex(params)+DigestUtils.md5Hex(_nowtime));//MD5������Կ��ʱ�����������
//		System.out.println(token);
		//Des���ܣ�ʹ�����ص�js��ִ��
		params = Js(params, _deskey);
		//���α�����ܴ���
		params = BaseChange(params,null);
//		System.out.println(params);
		//���Ļ�ϴ���
		String w [] = new String[3];
		w[0] = params;
		w[1] = token;
		w[2] = _nowtime;
		
//		params = "params=" + params + "&token="+token+"&timestamp="+_nowtime;
		return w;
	}
	
	/*����Base64����*/
	public String BaseChange(String uId,String SessionId) {
//		Base64.Decoder decoder = Base64.getDecoder();
		Base64.Encoder encoder = Base64.getEncoder();
		String text = null;
		if(SessionId!=null) {//��һ�λ��ǵڶ���
			text = uId+";;"+SessionId;//�ؼ�
		}else {
			text = uId;
		}
		byte[] textByte = null;
		try {
			textByte = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println(encoder.encodeToString(textByte));
		return encoder.encodeToString(textByte);
	}
	/*ʹ��js����     key��Կ*/
	public String Js(String params,String key) {
		// ��ýű�����
	    ScriptEngineManager sem = new ScriptEngineManager();
	    ScriptEngine engine = sem.getEngineByName("javascript");
	    
	    URL des = Code.class.getClassLoader().getResource("Js/jkingo.des.js");
//	    URL utf = JsTest.class.getClassLoader().getResource("Js/utf.js");//ִ�в�ִ�н��һ��
	    Reader r= null;
		try {
			 r = new FileReader(des.getPath());
			 engine.eval(r);
//			 r = new FileReader(utf.getPath());
//			 engine.eval(r);
		} catch (FileNotFoundException | ScriptException e) {
			e.printStackTrace();	
		}finally {
		 try {
			 r.close();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }  
		}
	    
	    // ȡ�õ��ýӿ�
	    Invocable jsInvoke = (Invocable) engine;
	    // ִ�нű��ж���ķ���
	    try {
			Object des_str = jsInvoke.invokeFunction("strEnc", new Object[] { params, key,null,null });
			params = (String) des_str;
//			System.out.println(des_str);
//			Object utf_str = jsInvoke.invokeFunction("utf16to8", new Object[] { des_str});
//			System.out.println(utf_str);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return params;
	}
	/*��ȡ��Կ��ʱ���*/
	public String [] GetKey(String cookie) throws ClientProtocolException, IOException {
		String  keyAndTime [] = new String [2];
		//ʵ����HttpClient
		HttpClient httpclient = HttpClients.custom().build();
		HttpGet httpget = new HttpGet("http://**.***.***.**:8081/hnjdjw/custom/js/SetKingoEncypt.jsp");
		httpget.setHeader("Cookie", cookie);
		//����ResponseHandler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		responseBody = httpclient.execute(httpget,responseHandler);
		httpget.abort();//�ͷ�����
		//��ȡ��Կ��ʱ���
		int a = responseBody.indexOf("'");
		int b = responseBody.indexOf("e = '");
		keyAndTime[0] =responseBody.substring(a+1, a+20);//��Կ
//		System.out.println("��Կ��"+keyAndTime[0]);
		keyAndTime[1] =responseBody.substring(b+5, b+24);//ʱ���
//		System.out.println("ʱ�����"+keyAndTime[1]);
		
		return keyAndTime;
	}
}
