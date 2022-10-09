package com.example.demo.pojo;

import lombok.Data;

import java.util.List;
@Data
public class DepartmentDto {
    public  int errcode;

    public  String errmsg;

    public List<Department> department;

    private static final long serialVersionUID = 1L;

}
