package com.lt.shop.dao.admin.entity.def;

import java.util.Date;

public class User {
    private Long id;

    private String uname;

    private String mobile;

    private String upwd;

    private String email;

    private String tname;

    private Integer mobileconfrim;

    private Integer emailconfrim;

    private String imghead;

    private String nickname;

    private Integer sex;

    private Date birth;

    private String mysign;

    private Integer flag;

    private String province;

    private String city;

    private String district;

    private String addr;

    private String idcard;

    private String frontimg;

    private String reversedimg;

    private String handimg;

    private Long regtime;

    private String openid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd == null ? null : upwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public Integer getMobileconfrim() {
        return mobileconfrim;
    }

    public void setMobileconfrim(Integer mobileconfrim) {
        this.mobileconfrim = mobileconfrim;
    }

    public Integer getEmailconfrim() {
        return emailconfrim;
    }

    public void setEmailconfrim(Integer emailconfrim) {
        this.emailconfrim = emailconfrim;
    }

    public String getImghead() {
        return imghead;
    }

    public void setImghead(String imghead) {
        this.imghead = imghead == null ? null : imghead.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getMysign() {
        return mysign;
    }

    public void setMysign(String mysign) {
        this.mysign = mysign == null ? null : mysign.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getFrontimg() {
        return frontimg;
    }

    public void setFrontimg(String frontimg) {
        this.frontimg = frontimg == null ? null : frontimg.trim();
    }

    public String getReversedimg() {
        return reversedimg;
    }

    public void setReversedimg(String reversedimg) {
        this.reversedimg = reversedimg == null ? null : reversedimg.trim();
    }

    public String getHandimg() {
        return handimg;
    }

    public void setHandimg(String handimg) {
        this.handimg = handimg == null ? null : handimg.trim();
    }

    public Long getRegtime() {
        return regtime;
    }

    public void setRegtime(Long regtime) {
        this.regtime = regtime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
}