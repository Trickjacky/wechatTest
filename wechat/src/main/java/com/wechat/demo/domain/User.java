package com.wechat.demo.domain;

public class User {

    private String id;

    private String name;

    private String pwd;

    private Integer useraddressid;

    private String fileglid;

    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getUseraddressid() {
        return useraddressid;
    }

    public void setUseraddressid(Integer useraddressid) {
        this.useraddressid = useraddressid;
    }

    public String getFileglid() {
        return fileglid;
    }

    public void setFileglid(String fileglid) {
        this.fileglid = fileglid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}