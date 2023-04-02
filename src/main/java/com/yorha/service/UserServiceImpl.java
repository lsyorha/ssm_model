package com.yorha.service;

import com.yorha.dao.UserMapper;
import com.yorha.dto.UserDTO;
import com.yorha.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User find(Integer id) {
        return convertModel2DTO(userMapper.find(id));
    }

    private UserDTO convertModel2DTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setAccount(user.getAccount());
        userDTO.setName(user.getName());
        return userDTO;
    }

}
