package com.example.demo.Service.impl;

import com.example.demo.Service.UserService;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(User resca) {
        User user1 = new User();
        BeanUtils.copyProperties(resca,user1);
        userMapper.insert(user1);
    }
}
