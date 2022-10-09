package com.example.demo.ulti;

import com.example.demo.pojo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class TokenUtil {

    @Autowired
    private RestTemplate restTemplate;

    public String token(String corpid,String corpsecret){

        //获取access_token路径
        String tokenurl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpid +"&corpsecret="+corpsecret;
        //获取响应内容
        ResponseEntity<Token> forEntity = restTemplate.getForEntity(tokenurl, Token.class);
        Token body = forEntity.getBody();
        //获取到token
        String access_token = body.getAccess_token();
        System.out.println(access_token);
        return access_token;
    }
}
