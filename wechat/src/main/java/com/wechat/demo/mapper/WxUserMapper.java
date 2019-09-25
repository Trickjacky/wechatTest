package com.wechat.demo.mapper;

import com.wechat.demo.domain.WxUser;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.common.Mapper;

public interface WxUserMapper extends Mapper<WxUser> {
    int updateWxUserSub(@Param(value = "wxUser") WxUser wxUser, @Param(value = "status") Integer status);
}