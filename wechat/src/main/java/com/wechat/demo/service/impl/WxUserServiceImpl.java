package com.wechat.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wechat.demo.mapper.WxUserMapper;
import com.wechat.demo.service.WxUserService;
@Service
public class WxUserServiceImpl implements WxUserService{

    @Resource
    private WxUserMapper wxUserMapper;

}
