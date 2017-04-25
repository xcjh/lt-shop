package com.lt.shop.dao.admin.entity.def;

import java.util.Date;

public class OrderLog {
    private Integer id;

    private Integer orderId;

    private Date ltime;

    private String ltype;

    private String lmemo;

    private String lremark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getLtime() {
        return ltime;
    }

    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    public String getLtype() {
        return ltype;
    }

    public void setLtype(String ltype) {
        this.ltype = ltype == null ? null : ltype.trim();
    }

    public String getLmemo() {
        return lmemo;
    }

    public void setLmemo(String lmemo) {
        this.lmemo = lmemo == null ? null : lmemo.trim();
    }

    public String getLremark() {
        return lremark;
    }

    public void setLremark(String lremark) {
        this.lremark = lremark == null ? null : lremark.trim();
    }
}