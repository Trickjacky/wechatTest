package com.wechat.demo.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "wx_user")
public class WxUser {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "JDBC")
    private Integer userId;

    /**
     * 用户openid，当前公众号用户唯一标识
     */
    @Column(name = "openid")
    private String openid;

    /**
     * 关注状态，0取消，1订阅
     */
    @Column(name = "subscribe")
    private Integer subscribe;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 性别，1是男，2是女，0是未知
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 国家
     */
    @Column(name = "country")
    private String country;

    /**
     * 省份
     */
    @Column(name = "province")
    private String province;

    /**
     * 语言
     */
    @Column(name = "`language`")
    private String language;

    /**
     * 头像地址
     */
    @Column(name = "headimgurl")
    private String headimgurl;

    /**
     * 关注时间
     */
    @Column(name = "subscribeTime")
    private Date subscribetime;

    /**
     * 多个公众号用户唯一标识
     */
    @Column(name = "unionid")
    private String unionid;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 分组
     */
    @Column(name = "groupid")
    private String groupid;

    /**
     * 会员等级
     */
    @Column(name = "grading")
    private Integer grading;

    /**
     * 余额
     */
    @Column(name = "balance")
    private Integer balance;

    /**
     * 积分
     */
    @Column(name = "integral_num")
    private Integer integralNum;

    /**
     * 积分有效期
     */
    @Column(name = "valid_time")
    private Date validTime;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户openid，当前公众号用户唯一标识
     *
     * @return openid - 用户openid，当前公众号用户唯一标识
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户openid，当前公众号用户唯一标识
     *
     * @param openid 用户openid，当前公众号用户唯一标识
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取关注状态，0取消，1订阅
     *
     * @return subscribe - 关注状态，0取消，1订阅
     */
    public Integer getSubscribe() {
        return subscribe;
    }

    /**
     * 设置关注状态，0取消，1订阅
     *
     * @param subscribe 关注状态，0取消，1订阅
     */
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取性别，1是男，2是女，0是未知
     *
     * @return sex - 性别，1是男，2是女，0是未知
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别，1是男，2是女，0是未知
     *
     * @param sex 性别，1是男，2是女，0是未知
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取头像地址
     *
     * @return headimgurl - 头像地址
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置头像地址
     *
     * @param headimgurl 头像地址
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 获取关注时间
     *
     * @return subscribeTime - 关注时间
     */
    public Date getSubscribetime() {
        return subscribetime;
    }

    /**
     * 设置关注时间
     *
     * @param subscribetime 关注时间
     */
    public void setSubscribetime(Date subscribetime) {
        this.subscribetime = subscribetime;
    }

    /**
     * 获取多个公众号用户唯一标识
     *
     * @return unionid - 多个公众号用户唯一标识
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设置多个公众号用户唯一标识
     *
     * @param unionid 多个公众号用户唯一标识
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取分组
     *
     * @return groupid - 分组
     */
    public String getGroupid() {
        return groupid;
    }

    /**
     * 设置分组
     *
     * @param groupid 分组
     */
    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取会员等级
     *
     * @return grading - 会员等级
     */
    public Integer getGrading() {
        return grading;
    }

    /**
     * 设置会员等级
     *
     * @param grading 会员等级
     */
    public void setGrading(Integer grading) {
        this.grading = grading;
    }

    /**
     * 获取余额
     *
     * @return balance - 余额
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * 获取积分
     *
     * @return integral_num - 积分
     */
    public Integer getIntegralNum() {
        return integralNum;
    }

    /**
     * 设置积分
     *
     * @param integralNum 积分
     */
    public void setIntegralNum(Integer integralNum) {
        this.integralNum = integralNum;
    }

    /**
     * 获取积分有效期
     *
     * @return valid_time - 积分有效期
     */
    public Date getValidTime() {
        return validTime;
    }

    /**
     * 设置积分有效期
     *
     * @param validTime 积分有效期
     */
    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }
}