package com.aruarian.test;

import java.io.IOException;
import java.util.Scanner;
import com.aruarian.util.*;

public class MainDemo {
	/*
	 * 1.运行程序前请先阅读README.md文件确保能正确运行。
	 * 2.**.***.***.** ip修改后才能运行
	 */
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		HttpClientDemo htc = new HttpClientDemo();
		String uId="";//学号
		String password="";//密码
		//获取验证码及cookie
		String code= "";
		String cookie = "";
			//将验证码爬取到images下并扫描
		PictureSpider ps = new PictureSpider();
//			String [] codeAndCookie = ps.getCodeAndCookie();
		do {
			cookie = ps.saveImage("http://**.***.***.**:8081/hnjdjw/cas/genValidateCode", "images/code.jpg");;
			OCR ocr = new OCR();
			code = ocr.Ocr();
		}while(code.length()!=4);
//			//混淆信息
			Code cd = new Code();
			System.out.print("输入学号：");
			uId=input.next();
			System.out.print("输入密码(默认值为学号)：");
			password=input.next();
			String codes [] = cd.getCode(code, cookie, uId, password);
			//提交登录表单信息
			Submit sub = new Submit();
			String message = sub.submit(codes, cookie);
			System.out.println(message);
			if(message.indexOf("操作成功")==-1) {
				/*
				 * 可能出现的问题:
				 * 服务器的问题：
				 * 1.连接超时：网络问题    解决办法：再次尝试运行程序
				 * 2.连接失败：服务器关闭了    解决办法：过半天后再次尝试即可
				 * 程序的问题：
				 * 1.验证码未正确识别出来。     解决办法：再次运行程序即可
				 * 3.错误的密码     解决办法：密码错误，不能超过限定次数，否则会锁号。
				 */
				System.exit(0);
			}
			ScoreData sdata = new ScoreData();
			sdata.showData(cookie);
		
	}
}
