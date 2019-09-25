package com.wechat.demo.mapper;

import com.wechat.demo.domain.Message;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MessageMapper extends Mapper<Message> {

    List<Message> selectMessagePush(String group);

}