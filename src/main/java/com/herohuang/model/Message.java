package com.herohuang.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 医生互动 互动信息
 */
public class Message implements Serializable {


    private BigDecimal liushuihao;

    private String keshiid;

    private String yishengid;
    
    private String yishengxm;

    private String xiaoxixx;

    private Date xiaoxisj;

    public String getYishengxm() {
        return yishengxm;
    }

    public void setYishengxm(String yishengxm) {
        this.yishengxm = yishengxm;
    }

    public BigDecimal getLiushuihao() {
        return liushuihao;
    }

    public void setLiushuihao(BigDecimal liushuihao) {
        this.liushuihao = liushuihao;
    }


    public String getKeshiid() {
        return keshiid;
    }

    public void setKeshiid(String keshiid) {
        this.keshiid = keshiid;
    }

    public String getYishengid() {
        return yishengid;
    }

    public void setYishengid(String yishengid) {
        this.yishengid = yishengid;
    }

    public String getXiaoxixx() {
        return xiaoxixx;
    }

    public void setXiaoxixx(String xiaoxixx) {
        this.xiaoxixx = xiaoxixx;
    }

    public Date getXiaoxisj() {
        return xiaoxisj;
    }

    public void setXiaoxisj(Date xiaoxisj) {
        this.xiaoxisj = xiaoxisj;
    }
}