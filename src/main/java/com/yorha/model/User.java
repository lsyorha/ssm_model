package com.yorha.model;

import java.io.Serializable;

//Serializable 实例化对象
public class User implements Serializable {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public User() {
  }

  public User(String id, String name, String account) {
    this.id = id;
    this.name = name;
    this.account = account;
  }

  private String name;
  private String account;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAccount() {
    return account;
  }

  @Override
  public String toString() {
    return "User{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", account='" + account + '\'' +
            '}';
  }
}
