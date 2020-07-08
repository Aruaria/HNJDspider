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
	 * @param code 验证码
	 * @param sessionId 
	 * @param uId 学号
	 * @param uPassword 密码
	 * @param return 编码结果
	 * 模拟加密编码，返回结果
	 */
	public String[] getCode(String code,String cookie,String uId,String uPassword) throws ClientProtocolException, IOException {
		String params = "";
		String p_username = "_u"+code;
		String p_password = "_p"+code;
		String sessionId = cookie.substring(11);
		String userName = BaseChange(uId,sessionId); //编码加密处理
		
		String password = DigestUtils.md5Hex(DigestUtils.md5Hex(uPassword)+DigestUtils.md5Hex(code));//MD5加密密码
		//进行混合加密  用户名 密码 验证码  固定的*关于的密码的规则
		params = p_username+"="+userName+"&"+p_password+"="+password+"&randnumber="+code+"&isPasswordPolicy="+"0"+
	             "&txt_mm_expression="+"8"+"&txt_mm_length="+"11"+"&txt_mm_userzh="+"1";
		//访问系统获得密钥和系统时间戳
		String keyAndTime [] = GetKey(cookie);
		String _deskey = keyAndTime[0];
		String _nowtime = keyAndTime[1];
		String token = DigestUtils.md5Hex(DigestUtils.md5Hex(params)+DigestUtils.md5Hex(_nowtime));//MD5加密密钥和时间戳生成令牌
//		System.out.println(token);
		//Des加密，使用下载的js来执行
		params = Js(params, _deskey);
		//二次编码加密处理
		params = BaseChange(params,null);
//		System.out.println(params);
		//最后的混合处理
		String w [] = new String[3];
		w[0] = params;
		w[1] = token;
		w[2] = _nowtime;
		
//		params = "params=" + params + "&token="+token+"&timestamp="+_nowtime;
		return w;
	}
	
	/*进行Base64编码*/
	public String BaseChange(String uId,String SessionId) {
//		Base64.Decoder decoder = Base64.getDecoder();
		Base64.Encoder encoder = Base64.getEncoder();
		String text = null;
		if(SessionId!=null) {//第一次还是第二次
			text = uId+";;"+SessionId;//关键
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
	/*使用js加密     key密钥*/
	public String Js(String params,String key) {
		// 获得脚本引擎
	    ScriptEngineManager sem = new ScriptEngineManager();
	    ScriptEngine engine = sem.getEngineByName("javascript");
	    
	    URL des = Code.class.getClassLoader().getResource("Js/jkingo.des.js");
//	    URL utf = JsTest.class.getClassLoader().getResource("Js/utf.js");//执行不执行结果一样
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
	    
	    // 取得调用接口
	    Invocable jsInvoke = (Invocable) engine;
	    // 执行脚本中定义的方法
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
	/*获取密钥和时间戳*/
	public String [] GetKey(String cookie) throws ClientProtocolException, IOException {
		String  keyAndTime [] = new String [2];
		//实例化HttpClient
		HttpClient httpclient = HttpClients.custom().build();
		HttpGet httpget = new HttpGet("http://**.***.***.**:8081/hnjdjw/custom/js/SetKingoEncypt.jsp");
		httpget.setHeader("Cookie", cookie);
		//构建ResponseHandler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		responseBody = httpclient.execute(httpget,responseHandler);
		httpget.abort();//释放连接
		//提取密钥和时间戳
		int a = responseBody.indexOf("'");
		int b = responseBody.indexOf("e = '");
		keyAndTime[0] =responseBody.substring(a+1, a+20);//密钥
//		System.out.println("密钥："+keyAndTime[0]);
		keyAndTime[1] =responseBody.substring(b+5, b+24);//时间戳
//		System.out.println("时间戳："+keyAndTime[1]);
		
		return keyAndTime;
	}
}
