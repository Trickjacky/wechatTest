package com.wechat.demo.controller;

import com.wechat.demo.commons.CheckWechatInfo;
import com.wechat.demo.commons.Constant;
import com.wechat.demo.commons.HttpClientUtil;
import com.wechat.demo.commons.MessageController;
import com.wechat.demo.domain.WxUser;
import com.wechat.demo.service.WxUserService;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class WechatController {

    @Resource(name = "wxUserServiceImpl")
    WxUserService wxUserService11;

    WxUser wxUser;

    String resultXml = "";

    MessageController messageController = new MessageController();

    /**
     * 完成项目与微信公众号的一次握手
     *
     * @param wechatInfo
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String indexGet(CheckWechatInfo wechatInfo) {
        //验证消息是否来源于微信
        boolean result = wechatInfo.checkInfo();
        if (result) {
            return wechatInfo.getEchostr();
        }
        return "";
    }

    @RequestMapping(value = "index", method = RequestMethod.POST)
    public String indexPost(HttpServletRequest request, HttpSession session) {
        try {
            Element root = new SAXBuilder().build(request.getInputStream()).getRootElement();
            //接收最终xml信息
            resultXml = messageController.msgType(root, session);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultXml;
    }

    @RequestMapping(value = "code", method = RequestMethod.POST)
    public String code(String code, HttpSession session) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constant.APPID + "&secret=" + Constant.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = HttpClientUtil.doGet(url);
        System.out.println(jsonObject);
        String url1 = "https://api.weixin.qq.com/sns/userinfo?access_token=" + jsonObject.get("access_token").toString() + "&openid=" + jsonObject.get("openid").toString() + "&lang=zh_CN";
        JSONObject jsonObject1 = HttpClientUtil.doGet(url1);
        System.out.println(jsonObject1);
        return "";
    }
}
