package com.lt.shop.dao.admin.entity.def;

import java.util.Date;

public class OrderLog {
    private Long id;

    private Long orderId;

    private Date ltime;

    private Integer ltype;

    private String lmemo;

    private String lremark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getLtime() {
        return ltime;
    }

    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    public Integer getLtype() {
        return ltype;
    }

    public void setLtype(Integer ltype) {
        this.ltype = ltype;
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