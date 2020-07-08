package com.aruarian.util;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class JsoupConnectUrl {

	/*����url*/
	@Test
	public void Test1() throws IOException {
		//��������
		Connection connect = Jsoup.connect("http://**.***.***.**:8081/hnjdjw/cas/login.action");
		//������ҳ��Ҳ����ʹ��post����
		Document document = connect.get();
		//���HTML
		System.out.println(document.html());
	}
	
	/*����ӦResponse,�ٻ�ȡHTML����*/
	@Test
	public void Test2 () throws IOException {
		//��ȡ��Ӧ
		Response response = Jsoup.connect("http://**.***.***.**/hnjdjw/cas/login.action").method(Method.GET).execute();
		URL url = response.url(); //�鿴�����url
		System.out.println("�����urlΪ"+url);
		int statusCode = response.statusCode();//��ȡ��Ӧ״̬��
		System.out.println("��Ӧ״̬��Ϊ��"+statusCode);
		String ContentType = response.contentType();
		//��ȡ��Ӧ��������
		System.out.println("��Ӧ����Ϊ��"+ContentType);
		//��Ӧ��Ϣ 200-ok
		String statusMessage = response.statusMessage();
		System.out.println("��Ӧ��ϢΪ��"+statusMessage);
		//�ж���Ӧ״̬���Ƿ�Ϊ200
		if(statusCode == 200) {
			//ͨ�����ַ�ʽ�ǿ��Ի����Ӧ��HTML�ļ�
			String html = new String(response.bodyAsBytes(),"GBK");
			//��ȡhtml���ݣ�����Ӧ����Document����
			Document document = response.parse();
			//����html��Document������һ���ģ���Document�Ǿ�����ʽ����
			System.out.println(html);
		}
	}
	
}
