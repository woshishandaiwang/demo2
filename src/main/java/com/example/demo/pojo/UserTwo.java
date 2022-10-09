package com.example.demo.pojo;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class UserTwo implements Serializable {

    private Integer id;

    private String userid;

    private String name;

    private List<Integer> department;

    private List<Integer> order;

    private String position;

    private String mobile;

    private String gender;

    private String email;

    private String biz_mail;

    private List<Integer> is_leader_in_dept;

    private List<String> direct_leader;

    private String avatar;

    private String status;

    private String isleader;

    private String thumb_avatar;

    private String telephone;

    private String enable;

    private String hide_mobile;

    private String qr_code;

    private String  alias;

    private String address;

    private String open_userid;

    private String main_department;

    private String  external_position;


    private static final long serialVersionUID = 1L;


}
