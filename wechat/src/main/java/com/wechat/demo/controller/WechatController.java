package com.wechat.demo.controller;

import com.wechat.demo.commons.CheckWechatInfo;
import com.wechat.demo.commons.Constant;
import com.wechat.demo.commons.HttpClientUtil;
import com.wechat.demo.commons.MessageUtil;
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
import java.util.HashMap;
import java.util.Map;

@RestController
public class WechatController {

    @Resource(name = "wxUserServiceImpl")
    WxUserService wxUserService11;

    WxUser wxUser;

    String resultXml = "";

    MessageUtil messageController = new MessageUtil();

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

    @RequestMapping(value = "config", method = RequestMethod.POST)
    public Map<String, Object> config(String tagetUrl, HttpSession session) {
        Map<String, Object> map = Constant.getConfig(tagetUrl);
        return map;
    }

    /**
     * 简易的二维码分享（写死数据）
     *
     * @param tagetUrl
     * @param session
     * @return
     */
    @RequestMapping(value = "shareDate", method = RequestMethod.POST)
    public Map<String, Object> shareDate(String tagetUrl, HttpSession session) {
        Map<String, Object> map = Constant.getConfig(tagetUrl);

        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + Constant.getAccess_token();
        String data = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 1}}}";
        JSONObject jsonObject = HttpClientUtil.doPost(url, data);
        String ticket = jsonObject.get("ticket").toString();
        System.out.println("ticket=" + ticket);

        //分享到朋友
        Map<String, String> appMessage = new HashMap<>();
        appMessage.put("title", "土豪快来助我！");
        appMessage.put("desc", "帮助好友获得土豪称谓，给他助力！");
        appMessage.put("link", "http://kj6xnfb.hn3.mofasuidao.cn/app/sweep_code.html?ticket=" + ticket);
        appMessage.put("imgUrl", "http://kj6xnfb.hn3.mofasuidao.cn/app/images/del-pic.jpg");
        //分享到朋友圈
        Map<String, String> timeline = new HashMap<String,String>();
        timeline.put("title", "土豪快来助我！");
        timeline.put("link", "http://kj6xnfb.hn3.mofasuidao.cn/app/sweep_code.html?ticket=" + ticket);
        timeline.put("imgUrl", "http://kj6xnfb.hn3.mofasuidao.cn/app/images/del-pic.jpg");

        map.put("appMessage", appMessage);
        map.put("timeline", timeline);

        return map;
    }
}
