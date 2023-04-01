package com.yorha.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Mood implements Serializable {
    public Integer getId() {
        return id;
    }

    public Mood() {
    }

    @Override
    public String toString() {
        return "Mood{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", publishTime=" + publishTime +
                ", publishNum=" + praiseNum +
                '}';
    }

    public Mood(Integer id, String content, Integer userId, Timestamp publishTime, Integer praiseNum) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.publishTime = publishTime;
        this.praiseNum = praiseNum;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    private Integer id;
    private String content;
    private Integer userId;
    private java.sql.Timestamp publishTime;
    private Integer praiseNum;
}
