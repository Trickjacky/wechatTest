package com.wechat.demo.service.impl;

import com.wechat.demo.domain.WxUser;
import com.wechat.demo.mapper.WxUserMapper;
import com.wechat.demo.service.WxUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Resource
    private WxUserMapper wxUserMapper;


    /**
     * 判断数据库中是否已存在
     *
     * @param wxUser
     * @return
     */
    @Override
    public boolean selectWxUser(WxUser wxUser) {
        WxUser wxUser2 = new WxUser();
        wxUser2.setOpenid(wxUser.getOpenid());
        WxUser wxUser1 = wxUserMapper.selectOne(wxUser2);
        if (wxUser1 == null) {
            //数据库中为空，增加
            return insertWxUser(wxUser) > 0;
        } else {
            //数据库中有数据，替换掉更新数据
            wxUser.setId(wxUser1.getId());
            return wxUserMapper.updateByPrimaryKeySelective(wxUser) > 0;
        }

    }

    @Override
    public int insertWxUser(WxUser wxUser) {
        return wxUserMapper.insertSelective(wxUser);
    }

    @Override
    public int updateWxUserSub(WxUser wxUser, Integer status) {
        return wxUserMapper.updateWxUserSub(wxUser, status);
    }

    @Override
    public WxUser selectWxUserByOpenId(String openId) {
        return wxUserMapper.selectWxUserByOpenId(openId);
    }

}
