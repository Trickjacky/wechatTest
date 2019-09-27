package com.wechat.demo.mapper;

import com.wechat.demo.domain.QrCode;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface QrCodeMapper extends Mapper<QrCode> {

    /**
     * 根据wx_id查询数据库中是否有创建二维码
     * @param wx_id
     * @return
     */
    QrCode selectByWx_id(Integer wx_id);

    /**
     * 根据id查询
     * @param qrcodeid
     * @return
     */
    QrCode selectById( Integer qrcodeid);

}