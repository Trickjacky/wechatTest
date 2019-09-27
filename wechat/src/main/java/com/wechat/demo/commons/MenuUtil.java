package com.wechat.demo.commons;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 获取access_token，用于调用接口
 */
public class MenuUtil {
    /**
     * 获取access_token
     *
     * @return
     */
    public static JSONObject getAccess_token() {
        //登录微信测试公众号分配的随机id
        String appid = "wxe17b0a2900af07f2";
        //登录微信测试公众号分配的随机密钥
        String appsecret = "29e1338328661ff65eee877589a4f6b6";
        //接口调用
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret;
        JSONObject jsonObject = HttpClientUtil.doGet(url);
        return jsonObject;
    }

    /**
     * 加载微信公众号底部自定义菜单
     *
     * @param args
     */
    public static void main(String[] args) {
        //JSONObject json = getAccess_token();
        String access_token = Constant.getAccess_token();
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
        //最外层json
        JSONObject big = new JSONObject();
        //一级菜单组
        JSONArray button = new JSONArray();
        //第一个一级菜单
        JSONObject menua = new JSONObject();
        menua.put("name", "首页");
        menua.put("type", "view");
        try {
            menua.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constant.APPID + "&redirect_uri=" + URLEncoder.encode("http://kj6xnfb.hn3.mofasuidao.cn/app/index.html", "utf-8") + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //第二个一级菜单
        JSONObject menub = new JSONObject();
        menub.put("name", "My car");
        menub.put("type", "view");
        try {
            menua.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constant.APPID + "&redirect_uri=" + URLEncoder.encode("http://kj6xnfb.hn3.mofasuidao.cn/app/shopcar.html", "utf-8") + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //第三个一级菜单
        JSONObject menuc = new JSONObject();
        menuc.put("name", "个人中心");
        menuc.put("type", "view");
        try {
            menua.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constant.APPID + "&redirect_uri=" + URLEncoder.encode("http://kj6xnfb.hn3.mofasuidao.cn/app/pcenter.html", "utf-8") + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //将3个一级菜单放入一级菜单组
        button.put(menua);
        button.put(menub);
        button.put(menuc);

        //将一级菜单组放入外层最大的json
        big.put("button", button);

        JSONObject jsonObject = HttpClientUtil.doPost(url, big.toString());
        System.out.println(jsonObject);

    }
}
