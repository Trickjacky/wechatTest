package com.wechat.demo.commons;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 检查wechat信息
 *
 * @author yang
 */
public class CheckWechatInfo {
    //微信加密签名
    private String signature;
    //时间戳
    private String timestamp;
    //随机数
    private String nonce;
    //随机字符串
    private String echostr;
    private final String token = "yang";

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    /**
     * 验证参数请求来源于微信
     *
     * @return
     */
    public boolean checkInfo() {
        List<String> list = new ArrayList<String>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i);
        }
        String sha1Str = getSha1(str);
        System.out.println("signature=" + signature);
        System.out.println("sha1Str=" + sha1Str);
        if (signature.equals(sha1Str)) {
            return true;
        }
        return false;
    }

    /**
     * sha1加密
     *
     * @param str
     * @return
     */
    public static String getSha1(String str) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public String toString() {
        return "CheckWechatInfo{" +
                "signature='" + signature + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", echostr='" + echostr + '\'' +
                '}';
    }
}
