package com.herohuang.model;

import java.io.Serializable;

/**
 * Created by acheron on 24/06/2017.
 */
public class User implements Serializable {
    private String userId;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(String userId, String userName) {

        this.userId = userId;
        this.userName = userName;
    }
}
