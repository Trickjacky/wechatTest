package com.wechat.demo.commons;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;


public class HttpClientUtil {
    //doGet请求
    public static JSONObject doGet(String url) {
        //设置请求格式
        HttpGet httpGet = new HttpGet(url);
        //创建客户端
        HttpClient httpClient = new DefaultHttpClient();
        try {
            return getJsonObject(httpClient.execute(httpGet), httpGet, httpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //提取公共方法
    private static JSONObject getJsonObject(HttpResponse execute, HttpRequestBase httpRequestBase, HttpClient httpClient) {
        JSONObject jsonObject = null;
        try {
            HttpResponse httpResponse = execute;
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String str = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                jsonObject = new JSONObject(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
    //doPost请求
    public static JSONObject doPost(String url, String data) {
        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = new DefaultHttpClient();
        httpPost.setEntity(new StringEntity                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          (data, "utf-8"));
        try {
            return getJsonObject(httpClient.execute(httpPost), httpPost, httpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
