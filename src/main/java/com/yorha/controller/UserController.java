package com.yorha.controller;

import com.yorha.model.User;
import com.yorha.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(@Qualifier("UserServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public String finAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("list",users);
        return "hello";
    }

    @GetMapping("/find")
    @ResponseBody
    public User find(){
        User user = userService.find(1);
        return user;
    }

}

