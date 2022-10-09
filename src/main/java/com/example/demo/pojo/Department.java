package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * department
 * @author 
 */
@Data
public class Department implements Serializable {


    /**
     * 部门id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门英文名称
     */
    private String nameen;

    /**
     * 部门领导
     */
    private List<String> department_leader;

    /**
     * 父部门id
     */
    private Integer parentid;

    /**
     * 在父部门中的次序值
     */
    private Integer order;

    private static final long serialVersionUID = 1L;


}