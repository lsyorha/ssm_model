package com.yorha.service;

import com.yorha.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    User find(Integer id);

}
