package com.example.demo;

import com.example.demo.pojo.Token;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class Demo2ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;
    @Test
    void contextLoads() {
     redisTemplate.opsForValue().set("kkkk","sdsd");
        Object kkkk = redisTemplate.opsForValue().get("kkkk");
        System.out.println(kkkk);

    }
    @Test
    void aaa(){
        //获取access_token路径
        String tokenurl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww423490154b2652d7&corpsecret=YBhCkLm0GVjs1Sg_jdMAuz3lUH0YvX04tC8Q-EIOTzU";
        //获取响应内容
        ResponseEntity<Token> forEntity = restTemplate.getForEntity(tokenurl, Token.class);
        Token body = forEntity.getBody();
        //获取到token
        String access_token = body.getAccess_token();
        System.out.println(access_token);
    }

}
