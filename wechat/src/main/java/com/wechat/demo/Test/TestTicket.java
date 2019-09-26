package com.wechat.demo.Test;

import com.wechat.demo.commons.Constant;
import com.wechat.demo.commons.HttpClientUtil;
import org.json.JSONObject;

public class TestTicket {
    /**
     * 生成随机二维码（写死）
     * @param args
     */
    public static void main(String[] args) {

        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + Constant.getAccess_token();

        String data = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 1}}}";

        JSONObject jsonObject = HttpClientUtil.doPost(url, data);
        System.out.println(jsonObject.toString());

    }
}
