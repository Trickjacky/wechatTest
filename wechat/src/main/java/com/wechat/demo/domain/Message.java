package com.wechat.demo.domain;

import javax.persistence.*;

@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 图片路径
     */
    @Column(name = "picUrl")
    private String picurl;

    /**
     * 跳转路径
     */
    @Column(name = "url")
    private String url;

    /**
     * 状态：-1禁用；0启用
     */
    @Column(name = "`status`")
    private String status;

    /**
     * 当前消息
     */
    @Column(name = "keyword")
    private String keyword;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取图片路径
     *
     * @return picUrl - 图片路径
     */
    public String getPicurl() {
        return picurl;
    }

    /**
     * 设置图片路径
     *
     * @param picurl 图片路径
     */
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    /**
     * 获取跳转路径
     *
     * @return url - 跳转路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置跳转路径
     *
     * @param url 跳转路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取状态：-1禁用；0启用
     *
     * @return status - 状态：-1禁用；0启用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：-1禁用；0启用
     *
     * @param status 状态：-1禁用；0启用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取当前消息
     *
     * @return keyword - 当前消息
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置当前消息
     *
     * @param keyword 当前消息
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}