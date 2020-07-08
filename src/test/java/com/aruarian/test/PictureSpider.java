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
 * ����֤����ȡ��images��
 */
public class PictureSpider {
	

	//����ĳһ��URL��������󵽵�����
	public static Object[]  getEntityByHttpGetMethod(String url,String cookie){
		HttpGet httpget = new HttpGet(url);
		//��ȡ���
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
		cookie = cookie.substring(cookie.indexOf("JS"), cookie.indexOf("JS")+43);//ȥ���޹��ַ�
//		System.out.println("Cookie:"+cookie);
		Object object [] = new Object [2]; 
		object[0]=entity;
		object[1]=cookie;
		return object;
	}
	//���������ַ���������ͼƬ
	static String saveImage(String url,String savePath) throws IOException {
		String cookie = "";
		//ͼƬ���ر����ַ
		File file = new File(savePath);
		//����ļ�������ɾ��
		if(file.exists()) {
			file.delete();
		}
		//������
		BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(savePath));
		//����ͼƬ����
		try {
			Object object [] = getEntityByHttpGetMethod(url,cookie);
			 HttpEntity entity = (HttpEntity)object[0];
			 cookie = (String)object[1];
			//���ֽڵķ�ʽд��
			byte [] byt = EntityUtils.toByteArray(entity);
			bw.write(byt);
//			System.out.println("ͼƬ�������");		
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw.close();
		return cookie;
	}
}
