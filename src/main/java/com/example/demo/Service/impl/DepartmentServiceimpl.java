package com.example.demo.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.DepartmentService;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.pojo.Department;
import com.example.demo.pojo.Token;
import com.example.demo.ulti.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentServiceimpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void add(String corpid, String corpsecret) {
        //创建对象
        Department department1 = new Department();

        //获取access_token路径
        String tokenurl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpid +"&corpsecret="+corpsecret;
        //获取响应内容
        ResponseEntity<Token> forEntity = restTemplate.getForEntity(tokenurl, Token.class);
        Token body = forEntity.getBody();
        //获取到token
        String access_token = body.getAccess_token();
        System.out.println(access_token);
//        TokenUtil tokenUtil = new TokenUtil();
//        String access_token = tokenUtil.token(corpid, corpsecret);
        //根据获取到token获取对应值
        String url= "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+access_token;
        Object forObject = restTemplate.getForObject(url, Object.class);
        JSONObject o = (JSONObject) JSON.toJSON(forObject);

        JSONArray department2 = o.getJSONArray("department");
        //System.out.println(department2);
        for (int i = 0; i < department2.size(); i++) {
            department1.setName(department2.getJSONObject(i).getString("name"));
            department1.setId(department2.getJSONObject(i).getInteger("id"));
            department1.setParentid(department2.getJSONObject(i).getInteger("parentid"));
            List department_leader1 = department2.getJSONObject(i).getObject("department_leader", List.class);
            department1.setDepartment_leader(department_leader1);
            department1.setNameen(department2.getJSONObject(i).getString("nameen"));
            department1.setOrder(department2.getJSONObject(i).getInteger("order"));
        }

        //调用mapper，进行数据持久化
        departmentMapper.Departmentinsert(department1);

   }
}
