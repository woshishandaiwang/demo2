package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Token {
    private String errcode;

    private String errmsg;

    private String access_token;

    private String expires_in;

    private static final long serialVersionUID = 1L;

}
