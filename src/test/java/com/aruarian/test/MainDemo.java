package com.aruarian.test;

import java.io.IOException;
import java.util.Scanner;
import com.aruarian.util.*;

public class MainDemo {
	/*
	 * 1.���г���ǰ�����Ķ�README.md�ļ�ȷ������ȷ���С�
	 * 2.**.***.***.** ip�޸ĺ��������
	 */
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		HttpClientDemo htc = new HttpClientDemo();
		String uId="";//ѧ��
		String password="";//����
		//��ȡ��֤�뼰cookie
		String code= "";
		String cookie = "";
			//����֤����ȡ��images�²�ɨ��
		PictureSpider ps = new PictureSpider();
//			String [] codeAndCookie = ps.getCodeAndCookie();
		do {
			cookie = ps.saveImage("http://**.***.***.**:8081/hnjdjw/cas/genValidateCode", "images/code.jpg");;
			OCR ocr = new OCR();
			code = ocr.Ocr();
		}while(code.length()!=4);
//			//������Ϣ
			Code cd = new Code();
			System.out.print("����ѧ�ţ�");
			uId=input.next();
			System.out.print("��������(Ĭ��ֵΪѧ��)��");
			password=input.next();
			String codes [] = cd.getCode(code, cookie, uId, password);
			//�ύ��¼����Ϣ
			Submit sub = new Submit();
			String message = sub.submit(codes, cookie);
			System.out.println(message);
			if(message.indexOf("�����ɹ�")==-1) {
				/*
				 * ���ܳ��ֵ�����:
				 * 1.��֤��δ��ȷʶ��������ٴ����г��򼴿�
				 * 2.Ŀ������������ˣ������һ���ٳ���
				 * 3.���������
				 */
				System.exit(0);
			}
			ScoreData sdata = new ScoreData();
			sdata.showData(cookie);
		
	}
}
