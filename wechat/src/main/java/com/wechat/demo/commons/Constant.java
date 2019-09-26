package com.wechat.demo.commons;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 获取静态资源
 */
public class Constant {
    //测试号使用
    public static final String APPID = "wxe17b0a2900af07f2";
    //测试号使用
    public static final String APPSECRET = "29e1338328661ff65eee877589a4f6b6";
    //存储access_token
    public static String ACCESS_TOKEN = null;
    //存储access_token的时间
    public static long ACCESS_TOKEN_TIME = 0L;
    //存储JSAPI_TICKET
    public static String JSAPI_TICKET = null;
    //存储jsapi_ticket的时间
    public static long JSAPI_TICKET_TIME;

    /**
     * 获取ACCESS_TOKEN并验证存在时间是否过期
     * 如果过期则重新获取
     *
     * @return
     */
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

    /**
     * 获取JSAPI_TICKET并验证存在时间是否过期
     * 如果过期则重新获取
     *
     * @return
     */
    public static String getJSAPI_ticket() {
        //现在的时间
        long now = System.currentTimeMillis() / 1000;
        //ACCESS_TOKEN有效时间7200秒
        if (JSAPI_TICKET == null || (now - JSAPI_TICKET_TIME) > 7200) {
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccess_token() + "&type=jsapi";
            JSONObject jsonObject = HttpClientUtil.doGet(url);
            if (jsonObject.get("errcode").toString().equals("0")) {
                JSAPI_TICKET = jsonObject.get("ticket").toString();
            }
        }
        return JSAPI_TICKET;
    }

    /**
     * 通过config接口注入权限并验证配置
     *
     * @param url
     * @return
     */
    public static Map<String, Object> getConfig(String url) {

        String jsapi_ticket = getJSAPI_ticket();
        //noncestr随机字符串
        String noncestr = UUID.randomUUID().toString().replaceAll("-", "");
        //时间戳
        long timestamp = System.currentTimeMillis() / 1000;
        //拼接成字符串
        String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        System.out.println(str);
        //进行sha1加密
        String signature = CheckWechatInfo.getSha1(str);
        System.out.println(signature);
        //将script中需要的数据存入map
        Map<String, Object> map = new HashMap<>();
        map.put("appId", APPID);
        map.put("timestamp", String.valueOf(timestamp));
        map.put("nonceStr", noncestr);
        map.put("signature", signature);
        return map;
    }
}
