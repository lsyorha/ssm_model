package com.yorha.dao;

import com.yorha.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    List<User> findAll();
//    查询用户
    User find(Integer id);

}
