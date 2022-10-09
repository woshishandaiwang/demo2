package com.example.demo.Controller;


import com.example.demo.Service.UserTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usertwo")
public class UserTwoController {
    @Autowired
    private UserTwoService userTwoService;

    @GetMapping("add")
    public void user(String corpid,String corpsecret ,int department_id){
        String add = userTwoService.add(corpid, corpsecret, department_id);
        System.out.println(add);
    }
}
