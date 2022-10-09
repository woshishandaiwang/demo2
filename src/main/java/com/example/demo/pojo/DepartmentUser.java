package com.example.demo.pojo;

import lombok.Data;

import java.util.List;
@Data
public class DepartmentUser {

    private String userid;

    private String name;

    private List<Integer> department;

    private String open_userid;


}
