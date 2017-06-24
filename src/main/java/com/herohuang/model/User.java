package com.herohuang.model;

import java.io.Serializable;

/**
 * Created by acheron on 24/06/2017.
 */
public class User implements Serializable {
    private String yishenid;
    private String yishenxm;

    public User(String yishenid, String yishenxm) {
        this.yishenid = yishenid;
        this.yishenxm = yishenxm;
    }

    public String getYishenid() {
        return yishenid;
    }

    public void setYishenid(String yishenid) {
        this.yishenid = yishenid;
    }

    public String getYishenxm() {
        return yishenxm;
    }

    public void setYishenxm(String yishenxm) {
        this.yishenxm = yishenxm;
    }
}
