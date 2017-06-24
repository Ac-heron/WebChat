package com.herohuang.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Message
 */
public class Message implements Serializable {

    private String roomId;

    private String userId;

    private String userName;

    private String content;

    private Date sendDate;

    public Message(String roomId, String userId, String userName, String content, Date sendDate) {
        this.roomId = roomId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.sendDate = sendDate;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message() {
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}