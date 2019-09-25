package com.wechat.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wechat.demo.mapper.MessageMapper;
import com.wechat.demo.service.MessageService;
@Service
public class MessageServiceImpl implements MessageService{

    @Resource
    private MessageMapper messageMapper;

}
