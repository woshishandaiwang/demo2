package com.example.demo.mapper;

import com.example.demo.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
    //添加管理部门

    void Departmentinsert( Department department);
}
