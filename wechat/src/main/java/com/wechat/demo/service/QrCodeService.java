package com.wechat.demo.service;

import com.wechat.demo.domain.QrCode;

public interface QrCodeService{

    QrCode updateQRcode(Integer wx_id);

    QrCode selectById(Integer qrcodeid);


}
