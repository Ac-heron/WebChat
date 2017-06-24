package com.herohuang.model;

import java.io.Serializable;

/**
 * Created by acheron on 24/06/2017.
 */
public class Room implements Serializable {

    private String bianma;

    private String mingcheng;

    public Room(String bianma, String mingcheng) {
        this.bianma = bianma;
        this.mingcheng = mingcheng;
    }

    public String getBianma() {
        return bianma;
    }

    public void setBianma(String bianma) {
        this.bianma = bianma;
    }

    public String getMingcheng() {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng;
    }
}
