package com.wechat.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wechat.demo.mapper.ShareQrcodeMapper;
import com.wechat.demo.service.ShareQrcodeService;
@Service
public class ShareQrcodeServiceImpl implements ShareQrcodeService{

    @Resource
    private ShareQrcodeMapper shareQrcodeMapper;

}
