package com.wechat.demo.service;

import com.wechat.demo.domain.WxUser;

public interface WxUserService {
    /**
     * 判断是否存在于数据库，如果不存在则增加
     * @param wxUser
     * @return
     */
    boolean selectWxUser(WxUser wxUser);

    int insertWxUser(WxUser wxUser);

    int updateWxUserSub(WxUser wxUser, Integer status);

    WxUser selectWxUserByOpenId(String openId);

}
