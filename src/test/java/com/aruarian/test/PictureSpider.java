package com.aruarian.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/*
 * 将验证码爬取到images下
 */
public class PictureSpider {
	

	//请求某一个URL，获得请求到的内容
	public static Object[]  getEntityByHttpGetMethod(String url,String cookie){
		HttpGet httpget = new HttpGet(url);
		//获取结果
		HttpResponse httpresponse = null;
		try {
			HttpClient httpclient = HttpClients.createDefault();
			httpresponse = httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = httpresponse.getEntity();
		Header[] h = httpresponse.getAllHeaders();
		cookie = h[2].toString();
		cookie = cookie.substring(cookie.indexOf("JS"), cookie.indexOf("JS")+43);//去掉无关字符
//		System.out.println("Cookie:"+cookie);
		Object object [] = new Object [2]; 
		object[0]=entity;
		object[1]=cookie;
		return object;
	}
	//输入任意地址便可以下载图片
	static String saveImage(String url,String savePath) throws IOException {
		String cookie = "";
		//图片下载保存地址
		File file = new File(savePath);
		//如果文件存在则删除
		if(file.exists()) {
			file.delete();
		}
		//缓冲流
		BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(savePath));
		//请求图片数据
		try {
			Object object [] = getEntityByHttpGetMethod(url,cookie);
			 HttpEntity entity = (HttpEntity)object[0];
			 cookie = (String)object[1];
			//以字节的方式写入
			byte [] byt = EntityUtils.toByteArray(entity);
			bw.write(byt);
//			System.out.println("图片下载完成");		
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw.close();
		return cookie;
	}
}
