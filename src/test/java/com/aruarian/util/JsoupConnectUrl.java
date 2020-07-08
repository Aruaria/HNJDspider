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

	/*请求url*/
	@Test
	public void Test1() throws IOException {
		//创建连接
		Connection connect = Jsoup.connect("http://**.***.***.**:8081/hnjdjw/cas/login.action");
		//请求网页，也可以使用post请求
		Document document = connect.get();
		//输出HTML
		System.out.println(document.html());
	}
	
	/*先响应Response,再获取HTML内容*/
	@Test
	public void Test2 () throws IOException {
		//获取响应
		Response response = Jsoup.connect("http://**.***.***.**/hnjdjw/cas/login.action").method(Method.GET).execute();
		URL url = response.url(); //查看请求的url
		System.out.println("请求的url为"+url);
		int statusCode = response.statusCode();//获取响应状态码
		System.out.println("响应状态码为："+statusCode);
		String ContentType = response.contentType();
		//获取响应类型数据
		System.out.println("响应类型为："+ContentType);
		//响应信息 200-ok
		String statusMessage = response.statusMessage();
		System.out.println("响应信息为："+statusMessage);
		//判断响应状态码是否为200
		if(statusCode == 200) {
			//通过这种方式是可以获得响应的HTML文件
			String html = new String(response.bodyAsBytes(),"GBK");
			//获取html内容，但对应的是Document类型
			Document document = response.parse();
			//这里html和Document数据是一样的，但Document是经过格式化的
			System.out.println(html);
		}
	}
	
}
