package com.wechat.demo.controller;

import com.wechat.demo.commons.*;
import com.wechat.demo.domain.QrCode;
import com.wechat.demo.domain.WxUser;
import com.wechat.demo.service.QrCodeService;
import com.wechat.demo.service.WxUserService;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

//该系统存入数据库未去重（待修改）
@RestController
public class WechatController {

    @Autowired
    QrCodeService qrCodeService;
    @Autowired
    WxUserService wxUserService;

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
    public String indexPost(HttpServletRequest request) {
        try {
            Element root = new SAXBuilder().build(request.getInputStream()).getRootElement();
            //接收最终xml信息
            resultXml = messageController.msgType(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultXml;
    }

    /**
     * 1、通过code换取网页授权的access_token和openid
     * 2、根据openid去数据库查询，用户是否存在
     * 3、是，直接将用户信息存入session
     * 4、否，调用网页授权中根据access_token和openid获取用户信息，存入数据库及session
     */
    @RequestMapping(value = "code", method = RequestMethod.POST)
    public String code(String code, HttpSession session) {

        System.out.println("获取code信息=" + code);
        //通过code换取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constant.APPID + "&secret=" + Constant.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
        //可以拿到access_token和openid
        JSONObject jsonObject = HttpClientUtil.doGet(url);
        //查询数据库中是否存在数据
        WxUser wxUser = wxUserService.selectWxUserByOpenId(jsonObject.get("openid").toString());
        if (wxUser == null) {
            //根据网页授权的access_token和openid去换取用户基本信息
            String url1 = "https://api.weixin.qq.com/sns/userinfo?access_token=" + jsonObject.get("access_token").toString() + "&openid=" + jsonObject.get("openid").toString() + "&lang=zh_CN";
            JSONObject jsonObject1 = HttpClientUtil.doGet(url1);
            wxUser = JsonUtil.fromJson(jsonObject1.toString(), WxUser.class);
            wxUserService.insertWxUser(wxUser);
        }
        session.setAttribute("wx_user", wxUserService.selectWxUserByOpenId(jsonObject.get("openid").toString()));
        System.out.println("codesession=" + wxUserService.selectWxUserByOpenId(jsonObject.get("openid").toString()));
        return "";

    }

    /**
     * 二维码分享
     *
     * @param tagetUrl
     * @param session
     * @return
     */
    @RequestMapping(value = "shareDate", method = RequestMethod.POST)
    public Map<String, Object> shareDate(String tagetUrl, HttpSession session) {

        //获取对象
        WxUser wx_user = (WxUser) session.getAttribute("wx_user");
        System.out.println("wx_user" + wx_user);
        //获取最新的二维码
        QrCode qrCode = qrCodeService.updateQRcode(wx_user.getId());

        Map<String, Object> map = Constant.getConfig(tagetUrl);
        //分享到朋友
        Map<String, String> appMessage = new HashMap<>();
        appMessage.put("title", "土豪快来助我！");
        appMessage.put("desc", "帮助好友获得土豪称谓，给他助力！");
        appMessage.put("link", "http://kj6xnfb.hn3.mofasuidao.cn/app/sweep_code.html?qrcodeid=" + qrCode.getId());
        appMessage.put("imgUrl", "http://kj6xnfb.hn3.mofasuidao.cn/app/images/del-pic.jpg");
        //分享到朋友圈
        Map<String, String> timeline = new HashMap<String, String>();
        timeline.put("title", "土豪快来助我！");
        timeline.put("link", "http://kj6xnfb.hn3.mofasuidao.cn/app/sweep_code.html?ticket=" + qrCode.getId());
        timeline.put("imgUrl", "http://kj6xnfb.hn3.mofasuidao.cn/app/images/del-pic.jpg");

        map.put("appMessage", appMessage);
        map.put("timeline", timeline);

        return map;
    }

    /**
     * 获取二维码
     *
     * @param qrcodeid
     * @return
     */
    @RequestMapping(value = "getQcode", method = RequestMethod.POST)
    public QrCode getQcode(Integer qrcodeid) {
        QrCode q = qrCodeService.selectById(qrcodeid);
        return q;
    }
}
