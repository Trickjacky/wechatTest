package com.wechat.demo.commons;

import org.json.JSONObject;

/**
 * 获取ACCESS_TOKEN并验证存在时间是否过期
 */
public class Constant {
    //测试号使用
    public static final String APPID = "wxe17b0a2900af07f2";
    //测试号使用
    public static final String APPSECRET = "29e1338328661ff65eee877589a4f6b6";

    public static String ACCESS_TOKEN = null;

    public static long ACCESS_TOKEN_TIME = 0L;

    public static String getAccess_token() {
        //现在的时间
        long now = System.currentTimeMillis() / 1000;
        //ACCESS_TOKEN有效时间7200秒
        if (ACCESS_TOKEN == null || (now - ACCESS_TOKEN_TIME) > 7200) {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
            JSONObject jsonObject = HttpClientUtil.doGet(url);
            //当时的时间
            ACCESS_TOKEN_TIME = System.currentTimeMillis() / 1000;
            ACCESS_TOKEN = jsonObject.get("access_token").toString();
        }
        return ACCESS_TOKEN;
    }
}
