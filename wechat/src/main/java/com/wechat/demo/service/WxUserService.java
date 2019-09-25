package com.wechat.demo.service;

import com.wechat.demo.domain.WxUser;

public interface WxUserService {
    boolean selectWxUser(WxUser wxUser);

    int insertWxUser(WxUser wxUser);

    int updateWxUserSub(WxUser wxUser, Integer status);

}
