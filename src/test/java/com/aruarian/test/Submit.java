package com.aruarian.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class Submit {
	
	public static String submit(String [] codes,String cookie) throws Exception {
        HttpPost httpPost = new HttpPost("http://61.163.103.43:8081/hnjdjw/cas/logon.action");
        httpPost.setHeader("Cookie", cookie);//不带cookie找不到值
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        HttpClient client = HttpClients.createDefault();
        String respContent = null;
//        表单方式
        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
        pairList.add(new BasicNameValuePair("params", codes[0]));
        pairList.add(new BasicNameValuePair("token", codes[1]));
        pairList.add(new BasicNameValuePair("timestamp", codes[2]));
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        return respContent;
    }
}
