package com.wechat.demo.service.impl;

import com.wechat.demo.commons.Constant;
import com.wechat.demo.commons.HttpClientUtil;
import com.wechat.demo.commons.JsonUtil;
import com.wechat.demo.domain.QrCode;
import com.wechat.demo.mapper.QrCodeMapper;
import com.wechat.demo.service.QrCodeService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Resource
    private QrCodeMapper qrCodeMapper;

    /**
     * 对二维码进行操作并返回最新的二维码
     *
     * @param wx_id
     * @return
     */
    @Override
    public QrCode updateQRcode(Integer wx_id) {
        /**
         * 1、通过用户id查询当前用户是否有二维码code
         * 2、如果有，判断是否过期
         * 2-1、如果过期，重新获取
         * 2-2、如果没有过期，使用数据库中的
         * 3、没有的话去获取
         */
        String expire_seconds = "2592000";
        String q_code = "";
        //查询当前用户是否有二维码
        QrCode qr_code = qrCodeMapper.selectByWx_id(wx_id);
        //如果没有的话，去获取
        if (qr_code == null) {
            QrCode qRcode = getQRcode(wx_id, expire_seconds);
            qRcode.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000));
            qRcode.setWxId(wx_id);
            qRcode.setExpireSeconds(expire_seconds);
            qrCodeMapper.insert(qRcode);
        } else {
            //如果有，判断是否过期
            //当前时间
            long nowTime = System.currentTimeMillis() / 1000;
            //创建时间
            long getTime = Long.valueOf(qr_code.getCreateTime());
            //有效期
            long yesTime = Long.valueOf(qr_code.getExpireSeconds());
            if ((nowTime - getTime) > yesTime) {
                //过期，重新获取
                QrCode qRcode = getQRcode(wx_id, expire_seconds);
                qRcode.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000));
                qRcode.setWxId(wx_id);
                qRcode.setExpireSeconds(expire_seconds);
                qrCodeMapper.updateByPrimaryKey(qRcode);
            }
        }
        return qrCodeMapper.selectByWx_id(wx_id);

    }

    @Override
    public QrCode selectById(Integer qrcodeid) {
        return qrCodeMapper.selectById(qrcodeid);
    }

    public QrCode getQRcode(Integer wx_id, String expire_seconds) {

        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + Constant.getAccess_token();
        String data = "{\"expire_seconds\": " + expire_seconds + ", \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": " + wx_id + "}}}";
        JSONObject jsonObject = HttpClientUtil.doPost(url, data);
        //得到二维码
        System.out.println("jsonObject.toString()=" + jsonObject.toString());
        QrCode qr_code = JsonUtil.fromJson(jsonObject.toString(), QrCode.class);
        return qr_code;
    }
}
