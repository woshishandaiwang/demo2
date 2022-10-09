package com.example.demo.Controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.DepartmentService;
import com.example.demo.pojo.Department;
import com.example.demo.pojo.DepartmentDto;

import com.example.demo.pojo.Token;


import com.example.demo.ulti.Changelist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("list")
    public void departments(String corpid,String corpsecret){

        //调用service进行数据添加
       departmentService.add(corpid,corpsecret);
    }
}
