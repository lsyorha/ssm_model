package com.yorha.dto;

import com.yorha.model.Mood;


import java.sql.Timestamp;


public class MoodDTO extends Mood {
    public String getUserName() {
        return userName;
    }

    public MoodDTO(String userName, String userAccount) {
        this.userName = userName;
        this.userAccount = userAccount;
    }

    public MoodDTO() {
    }

    @Override
    public String toString(){
        return "MoodDTO{" +
                "userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\''+
                ", id=" + getId() + '\'' +
                ", content='" + getContent() + '\'' +
                ", userId=" + getUserId() + '\'' +
                ", publishTime=" + getPublishTime() + '\'' +
                ", publishNum=" + getPraiseNum()+
                '}';
    }

    public MoodDTO(Integer id, String content, Integer userId, Timestamp publishTime, Integer praiseNum, String userName, String userAccount) {
        super(id, content, userId, publishTime, praiseNum);
        this.userName = userName;
        this.userAccount = userAccount;
    }

    public MoodDTO(Integer id, String content, Integer userId, Timestamp publishTime, Integer praiseNum) {
        super(id, content, userId, publishTime, praiseNum);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    //    用户名
    private String userName;
//    用户账号
    private String userAccount;
}
