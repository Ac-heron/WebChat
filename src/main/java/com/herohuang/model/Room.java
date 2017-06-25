package com.herohuang.model;

import java.io.Serializable;

/**
 * Created by acheron on 24/06/2017.
 */
public class Room implements Serializable {

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room(String code, String name) {

        this.code = code;
        this.name = name;
    }
}

