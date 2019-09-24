package com.wechat.demo.controller;

import com.wechat.demo.commons.CheckWechatInfo;
import com.wechat.demo.commons.Constant;
import com.wechat.demo.commons.HttpClientUtil;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    /**
     * 完成项目与微信公众号的一次握手
     *
     * @param wechatInfo
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String indexGet(CheckWechatInfo wechatInfo) {
        boolean result = wechatInfo.checkInfo();
        if (result) {
            return wechatInfo.getEchostr();
        }
        return "";
    }

    @RequestMapping(value = "index", method = RequestMethod.POST)
    public String indexPost(HttpServletRequest request) {
        try {
            Element root = new SAXBuilder().build(request.getInputStream()).getRootElement();
            //开发者微信号
            String toUserName = root.getChildText("ToUserName");
            //发送方帐号（一个OpenID）
            String fromUserName = root.getChildText("FromUserName");
            //消息创建时间（整型）
            String createTime = root.getChildText("CreateTime");
            //消息类型，event
            String msgType = root.getChildText("MsgType");


            //控制台输出语句
            System.out.println("ToUserName=" + toUserName);
            System.out.println("FromUserName=" + fromUserName);
            System.out.println("CreateTime=" + createTime);
            System.out.println("MsgType=" + msgType);


            if (msgType.equals("event")) {
                //进入事件类型消息
                System.out.println("进入事件类型消息");
                //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
                String event = root.getChildText("Event");
                if (event.equals("subscribe")) {
                    System.out.println("进入关注事件");
                    /**
                     * 获取用户信息并传入数据库
                     */
                    //根据获取到的openid去查询用户基本信息
                    String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Constant.getAccess_token() + "&openid=" + fromUserName + "&lang=zh_CN";
                    JSONObject jsonObject = HttpClientUtil.doGet(url);
                    System.out.println(jsonObject.toString());

                } else if (event.equals("unsubscribe")) {
                    System.out.println("进入取消关注事件");
                }

            } else if (msgType.equals("text")) {
                //进入文本消息
                System.out.println("进入文本消息");
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
