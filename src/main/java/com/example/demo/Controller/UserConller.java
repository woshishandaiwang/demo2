package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserConller {
    @Autowired
    private UserService userService;
    //添加用户
    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.add(user);
    }
}
