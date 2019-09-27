package com.wechat.demo.domain;

import javax.persistence.*;

@Table(name = "share_qrcode")
public class ShareQrcode {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "f_id")
    private Integer fId;

    @Column(name = "s_id")
    private Integer sId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return f_id
     */
    public Integer getfId() {
        return fId;
    }

    /**
     * @param fId
     */
    public void setfId(Integer fId) {
        this.fId = fId;
    }

    /**
     * @return s_id
     */
    public Integer getsId() {
        return sId;
    }

    /**
     * @param sId
     */
    public void setsId(Integer sId) {
        this.sId = sId;
    }
}