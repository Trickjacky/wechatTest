package com.wechat.demo.commons;

import com.wechat.demo.domain.Message;
import com.wechat.demo.domain.QrCode;
import com.wechat.demo.domain.ShareQrcode;
import com.wechat.demo.domain.WxUser;
import com.wechat.demo.mapper.MessageMapper;
import com.wechat.demo.mapper.QrCodeMapper;
import com.wechat.demo.mapper.ShareQrcodeMapper;
import com.wechat.demo.service.WxUserService;
import com.wechat.demo.service.impl.WxUserServiceImpl;
import org.jdom2.CDATA;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONObject;


import java.net.URLEncoder;
import java.util.List;


public class MessageUtil {

    String xml = "";

    WxUser d_wx_user = null;

    /**
     * 普通类调用注解类
     */
    WxUserService wxUserService = SpringBeanUtil.getBean(WxUserServiceImpl.class);
    MessageMapper messageMapper = SpringBeanUtil.getBean(MessageMapper.class);
    QrCodeMapper qrCodeMapper = SpringBeanUtil.getBean(QrCodeMapper.class);
    ShareQrcodeMapper shareQrcodeMapper = SpringBeanUtil.getBean(ShareQrcodeMapper.class);

    public String eventUpdate(String event, String fromUserName, String toUserName, String eventKey, String ticket) {

        if (event.equals("subscribe")) {
            System.out.println("进入关注事件");
            System.out.println(eventKey);
            //截取场景值
            String[] split = eventKey.split("_");

            /**
             * 获取用户信息并传入数据库
             */
            //根据获取到的openid去查询用户基本信息
            String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Constant.getAccess_token() + "&openid=" + fromUserName + "&lang=zh_CN";
            JSONObject jsonObject = HttpClientUtil.doGet(url);
            System.out.println(jsonObject.toString());
            d_wx_user = JsonUtil.fromJson(jsonObject.toString(), WxUser.class);
            System.out.println("d_wx_user=" + d_wx_user);
            wxUserService.selectWxUser(d_wx_user);
            try {
                //查询关注的推送消息
                List<Message> list = messageMapper.selectMessagePush("0");
                xml = createResultXml(list, fromUserName, toUserName, "news");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (split.length > 1) {
                String scene_id = split[1];
                /**
                 * 如果有场景值，那么代表当前这个用户是通过扫描带有参数的二维码进来的
                 * 与我们的分享活动有关
                 * 需要将当前用户的信息与分享者进行绑定
                 */

                System.out.println("scene_id=" + scene_id);
                QrCode qrCode = qrCodeMapper.selectByWx_id(Integer.valueOf(scene_id));
                System.out.println("qrCode.toString()=" + qrCode.toString());
                ShareQrcode shareQrcode = new ShareQrcode();
                //存入分享者id
                shareQrcode.setfId(qrCode.getWxId());
                d_wx_user = wxUserService.selectWxUserByOpenId(String.valueOf(jsonObject.get("openid")));
                //存入扫码者id（通过分享出去的连接扫码）
                shareQrcode.setsId(d_wx_user.getId());
                shareQrcodeMapper.insert(shareQrcode);
            }


        } else if (event.equals("unsubscribe")) {
            System.out.println("进入取消关注事件");
            //根据获取到的openid去查询用户基本信息
            String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Constant.getAccess_token() + "&openid=" + fromUserName + "&lang=zh_CN";
            JSONObject jsonObject = HttpClientUtil.doGet(url);
            System.out.println(jsonObject.toString());
            d_wx_user = JsonUtil.fromJson(jsonObject.toString(), WxUser.class);
            wxUserService.updateWxUserSub(d_wx_user, 0);
        }
        return xml;
    }

    public String msgType(Element root) {

        //开发者微信号
        String toUserName = root.getChildText("ToUserName");
        //用户的openid
        String fromUserName = root.getChildText("FromUserName");
        //消息创建时间
        String createTime = root.getChildText("CreateTime");
        //消息类型（值为event时消息类型为事件类型）
        String msgType = root.getChildText("MsgType");
        //当消息类型为事件类型时，有值
        String event = root.getChildText("Event");
        //事件KEY值，qrscene为前缀，后面为二维码的参数值
        String eventKey = root.getChildText("EventKey");
        //二维码的ticket，可用来换取二维码图片
        String ticket = root.getChildText("Ticket");
        //根据openid查询用户是否存在
        if (msgType.equals("event")) {
            //进入事件类型消息
            System.out.println("进入事件类型消息");
            //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
            xml = eventUpdate(event, fromUserName, toUserName, eventKey, ticket);
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

        return xml;
    }

    /**
     * 遍历list，创建xml格式字符串
     *
     * @param list
     * @return
     */
    private String createResultXml(List<Message> list, String fromUserName, String toUserName, String msgType) {
        //创建xml 等节点
        Element xml = new Element("xml");
        xml.addContent(new Element("ToUserName").addContent(new CDATA(fromUserName)));
        xml.addContent(new Element("FromUserName").addContent(new CDATA(toUserName)));
        xml.addContent(new Element("CreateTime").addContent(new CDATA(String.valueOf(System.currentTimeMillis()))));
        xml.addContent(new Element("MsgType").addContent(new CDATA(msgType)));
        xml.addContent(new Element("ArticleCount").addContent(String.valueOf(list.size())));
        Element articles = new Element("Articles");
        //循环取出数据，放入articles节点
        for (int i = 0; i < list.size(); i++) {
            Message message = list.get(i);
            Element item = new Element("item");
            item.addContent(new Element("Title").addContent(new CDATA(message.getTitle())));
            item.addContent(new Element("Description").addContent(new CDATA(message.getDescription())));
            item.addContent(new Element("PicUrl").addContent(new CDATA(message.getPicurl())));

            try {
                String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constant.APPID + "&redirect_uri=" + URLEncoder.encode(message.getUrl(), "utf-8") +
                        "&response_type=code&scope=snsapi_userinfo&state=STATE" +
                        "#wechat_redirect";
                item.addContent(new Element("Url").addContent(new CDATA(url)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            articles.addContent(item);
        }
        //将articles节点放入xml节点内
        xml.addContent(articles);
        // 设置xml文件的字符为UTF-8，解决中文问题
        Format format = Format.getCompactFormat();
        format.setEncoding("UTF-8");
        XMLOutputter xmlout = new XMLOutputter();
        //转为String类型的xml格式
        return xmlout.outputString(xml);
    }

}
