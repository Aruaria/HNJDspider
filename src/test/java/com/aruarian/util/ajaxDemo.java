package com.aruarian.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * @author aruarian
 * 模拟post发送ajax请求
 */
public class ajaxDemo {
	
	public static String httpPostWithJSON(String url) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cookie", "JSESSIONID=xxxxxxxxxxxxxxxxxxxxxx");//不带cookie找不到值
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;

//        json方式
//        JSONObject jsonParam = new JSONObject();
//        jsonParam.put("name", "admin");
//        jsonParam.put("pass", "123456");
//        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
//        entity.setContentEncoding("UTF-8");
//        entity.setContentType("application/json");
//        httpPost.setEntity(entity);
//        System.out.println();


//        表单方式
        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
        pairList.add(new BasicNameValuePair("params", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        pairList.add(new BasicNameValuePair("token", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
        pairList.add(new BasicNameValuePair("timestamp", "xxxxxxxxxxxxxxx"));
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));


        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        return respContent;
    }


    public static void main(String[] args) throws Exception {
        String result = httpPostWithJSON("http://61.163.103.43:8081/hnjdjw/cas/logon.action");
        System.out.println(result);
    }
}
	