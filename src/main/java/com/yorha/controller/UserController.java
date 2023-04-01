package com.yorha.controller;

import com.yorha.model.User;
import com.yorha.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private  UserService userService;

    public UserController( UserService userService) {
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
        return userService.find(1);
    }

}

