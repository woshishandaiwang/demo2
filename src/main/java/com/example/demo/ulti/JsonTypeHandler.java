package com.example.demo.ulti;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 转换成json数据
 */
public class JsonTypeHandler implements TypeHandler {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        try {
            preparedStatement.setString(i, JSONObject.toJSONString(o));
        } catch (Exception e) {
            throw new SQLException("字段处理失败");
        }
    }

    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        return this.decryptData(resultSet.getString(s));
    }

    @Override
    public Object getResult(ResultSet resultSet, int i) throws SQLException {
        return this.decryptData(resultSet.getString(i));
    }

    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        return this.decryptData(callableStatement.getString(i));
    }

    private Object decryptData(String string) throws SQLException {
        try {
            if(string.indexOf("{") == 0){
                return JSONObject.parseObject(string);
            }else{
                return JSONArray.parseArray(string);
            }
        } catch (Exception e) {
            throw new SQLException("字段处理失败");
        }
    }
}
