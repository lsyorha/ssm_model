package com.yorha.dto;

import com.yorha.model.User;
//俩个DTO主要用于前端展示
public class UserDTO extends User {
    public UserDTO(String id, String name, String account) {
        super(id, name, account);
    }

    public UserDTO() {
    }
}
