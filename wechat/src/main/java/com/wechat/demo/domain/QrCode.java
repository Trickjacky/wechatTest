package com.wechat.demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "qr_code")
public class QrCode {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "wx_id")
    private Integer wxId;

    @Column(name = "ticket")
    private String ticket;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "expire_seconds")
    private String expireSeconds;
    @Transient
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
     * @return wx_id
     */
    public Integer getWxId() {
        return wxId;
    }

    /**
     * @param wxId
     */
    public void setWxId(Integer wxId) {
        this.wxId = wxId;
    }

    /**
     * @return ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * @param ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * @return create_time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return expire_seconds
     */
    public String getExpireSeconds() {
        return expireSeconds;
    }

    /**
     * @param expireSeconds
     */
    public void setExpireSeconds(String expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}