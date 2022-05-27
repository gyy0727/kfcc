package com.luoxin.www.dao;

import com.luoxin.www.ioc.IocAnnotation;

import java.sql.SQLException;
import java.util.List;
@IocAnnotation
public interface MysqlRestaurant {
    public void addIntoMysql(String sql01,Object...args) throws SQLException;
    public List selectFormMysql(String sql04, Object...args) throws SQLException;
}
