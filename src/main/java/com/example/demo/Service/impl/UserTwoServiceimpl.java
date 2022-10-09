package com.example.demo.Service.impl;

import com.example.demo.Service.UserTwoService;
import com.example.demo.mapper.UserTwoMapper;
import com.example.demo.pojo.*;
import com.example.demo.ulti.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserTwoServiceimpl implements UserTwoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserTwoMapper userTwoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

@Override
public String add(String corpid, String corpsecret,int department_id) {
//        TokenUtil tokenUtil = new TokenUtil();
//        String access_token1 = tokenUtil.token(corpid, corpsecret);


       //获取token
       Object token = redisTemplate.opsForValue().get("token");
        String access_token1;
        if (token==null){
        //获取access_token路径
        String tokenurl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpid +"&corpsecret="+corpsecret;
        //获取响应内容
        ResponseEntity<Token> forEntity = restTemplate.getForEntity(tokenurl, Token.class);
        Token body = forEntity.getBody();

        //状态进行判断
        String errcode = body.getErrcode();
        if (errcode!="0"){
            return "获取token连接失败";
        }
        //获取到token
        String access_token = body.getAccess_token();
        access_token1=access_token;
        redisTemplate.opsForValue().set("token",access_token);
       }else {
        String access_token = token.toString();
        access_token1=access_token;
    }

        //根据获取到token获取对应值
        String url= "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+access_token1+"&department_id="+department_id;

        Object forObject1 = restTemplate.getForObject(url, Object.class);
        Map map = (Map)forObject1;
        String errcode1 = map.get("errcode").toString();
        if (errcode1!="0"){
            return "获取部门成员失败";
        }
        //获取内容
        System.out.println(forObject1);
        //进行转换
        ObjectMapper objectMapper = new ObjectMapper();
        DepartmentUserDto departmentUserDto = objectMapper.convertValue(forObject1, DepartmentUserDto.class);
        //遍历
        List<DepartmentUser> userlist = departmentUserDto.getUserlist();

        for (DepartmentUser departmentUser : userlist) {
            //创建持久化对象
            UserTwo userTwo = new UserTwo();
            //获取成员uuid
            String userid = departmentUser.getUserid();
            System.out.println(userid);

            //进行遍历添加
            //根据获取到token获取对应值
            String userurl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+access_token1+"&userid="+userid;
            Object forObject = restTemplate.getForObject(userurl, Object.class);

            // 装换成map
            Map entity = (Map)forObject;
            //对errcode进行判断
            String errcode2 = entity.get("errcode").toString();
            if (errcode1!="0"){
                return "获取成员信息失败";
            }
            //存储到userTwo
            userTwo.setName( entity.get("name").toString());
            userTwo.setUserid(entity.get("userid").toString());
            //类型进行转换
            List department = (List<String>)entity.get("department");
            userTwo.setDepartment(department);
            userTwo.setPosition(entity.get("position").toString());
            userTwo.setMobile(entity.get("mobile").toString());
            userTwo.setGender(entity.get("gender").toString());
            userTwo.setEmail(entity.get("email").toString());
            userTwo.setAvatar(entity.get("avatar").toString());
            userTwo.setStatus(entity.get("status").toString());
            userTwo.setIsleader(entity.get("isleader").toString());
            userTwo.setTelephone(entity.get("telephone").toString());
            userTwo.setEnable(entity.get("enable").toString());
            userTwo.setHide_mobile(entity.get("hide_mobile").toString());
            List order = (List<String>)entity.get("order");
            userTwo.setOrder(order);
            userTwo.setMain_department(entity.get("main_department").toString());
            userTwo.setQr_code(entity.get("qr_code").toString());
            userTwo.setAlias(entity.get("alias").toString());
            List is_leader_in_dept =(List<String>) entity.get("is_leader_in_dept");
            userTwo.setIs_leader_in_dept(is_leader_in_dept);
            userTwo.setThumb_avatar(entity.get("thumb_avatar").toString());
            List direct_leader = (List<String>)entity.get("direct_leader");
            userTwo.setDirect_leader(direct_leader);
            userTwo.setBiz_mail(entity.get("biz_mail").toString());
            //持久化到数据库里
            userTwoMapper.insert(userTwo);
        }
        return "通过";
    }
}
