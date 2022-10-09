package com.example.demo.mapper;

import com.example.demo.pojo.UserTwo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTwoMapper {
    //添加成员
    void insert( UserTwo userTwo);
}
