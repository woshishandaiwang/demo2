package com.example.demo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentUserDto {
    public  int errcode;

    public  String errmsg;

    public List<DepartmentUser> userlist;
}
