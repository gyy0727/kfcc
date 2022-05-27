package com.luoxin.www.dao;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.*;

public class MysqlUserImplTest {

    @BeforeMethod
    public void setUp() {
    }
    @Test
    public void testModifyFormMysql() {

    }

    @Test
    public void testSelectFormMysql() throws SQLException {
        String sql = "select * from admin ";
        MysqlUserImpl mysqlUser=new MysqlUserImpl();
        mysqlUser.selectFormMysql(sql);
    }
}