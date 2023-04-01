package com.yorha.model;



public class UserMoodPraiseRel {
  public UserMoodPraiseRel(Integer id, Integer userId, Integer moodId) {
    this.id = id;
    this.userId = userId;
    this.moodId = moodId;
  }

  public UserMoodPraiseRel() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getMoodId() {
    return moodId;
  }

  public void setMoodId(Integer moodId) {
    this.moodId = moodId;
  }

  private Integer id;
  private Integer userId;
  private Integer moodId;

  @Override
  public String toString() {
    return "UserMoodPraiseRel{" +
            "id=" + id +
            ", userId=" + userId +
            ", moodId=" + moodId +
            '}';
  }
}
