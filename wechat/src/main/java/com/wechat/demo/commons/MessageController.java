package com.wechat.demo.commons;

import com.wechat.demo.domain.WxUser;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class MessageController {

    String xml = "";
    // @Resource(name = "wxUserServiceImpl")
    // private WxUserService wxUserService;

    public String eventUpdate(String event, String fromUserName, String toUserName) {
        if (event.equals("subscribe")) {
            System.out.println("进入关注事件");
            /**
             * 获取用户信息并传入数据库
             */
            //根据获取到的openid去查询用户基本信息
            String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Constant.getAccess_token() + "&openid=" + fromUserName + "&lang=zh_CN";
            JSONObject jsonObject = HttpClientUtil.doGet(url);
            System.out.println(jsonObject.toString());
            WxUser wxUser = JsonUtil.fromJson(jsonObject.toString(), WxUser.class);
            System.out.println("wxUser=" + wxUser);
            // wxUserService.selectWxUser(wxUser);
            xml = "<xml>\n" +
                    "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[news]]></MsgType>\n" +
                    "  <ArticleCount>2</ArticleCount>\n" +
                    "  <Articles>\n" +
                    "    <item>\n" +
                    "      <Title><![CDATA[大标题]]></Title>\n" +
                    "      <Description><![CDATA[大标题描述]]></Description>\n" +
                    "      <PicUrl><![CDATA[http://kj6xnfb.hn3.mofasuidao.cn/app/img/logo.png]]></PicUrl>\n" +
                    "      <Url><![CDATA[http://kj6xnfb.hn3.mofasuidao.cn/app/index.html]]></Url>\n" +
                    "    </item>\n" +
                    "<item>\n" +
                    "      <Title><![CDATA[第二标题]]></Title>\n" +
                    "      <Description><![CDATA[第二标题的描述]]></Description>\n" +
                    "      <PicUrl><![CDATA[http://kj6xnfb.hn3.mofasuidao.cn/app/img/rmen.png]]></PicUrl>\n" +
                    "      <Url><![CDATA[http://kj6xnfb.hn3.mofasuidao.cn/app/pcenter.html]]></Url>\n" +
                    "    </item>" +
                    "  </Articles>\n" +
                    "</xml>";
        } else if (event.equals("unsubscribe")) {
            System.out.println("进入取消关注事件");
            //根据获取到的openid去查询用户基本信息
            String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Constant.getAccess_token() + "&openid=" + fromUserName + "&lang=zh_CN";
            JSONObject jsonObject = HttpClientUtil.doGet(url);
            System.out.println(jsonObject.toString());
            WxUser wxUser = JsonUtil.fromJson(jsonObject.toString(), WxUser.class);
            // wxUserService.updateWxUserSub(wxUser, 0);
        }
        return xml;
    }

    public String msgType(String msgType, String event, String fromUserName, String toUserName) {
        if (msgType.equals("event")) {
            //进入事件类型消息
            System.out.println("进入事件类型消息");
            //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
            xml = eventUpdate(event, fromUserName, toUserName);
            return xml;
        } else if (msgType.equals("text")) {
            //进入文本消息
            System.out.println("进入文本消息");

            xml = "<xml>\n" +
                    "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                    "  <Content><![CDATA[你好]]></Content>\n" +
                    "</xml>";
            return xml;

        } else if (msgType.equals("image")) {
            //进入图片消息
            System.out.println("进入图片消息");
        } else if (msgType.equals("voice")) {
            //进入语音消息
            System.out.println("进入语音消息");
        } else if (msgType.equals("video")) {
            //进入视频消息
            System.out.println("进入视频消息");
        } else if (msgType.equals("shortvideo")) {
            //进入小视频消息
            System.out.println("进入小视频消息");
        }
        return "";
    }

}
