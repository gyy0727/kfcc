package com.luoxin.www.dao;

import com.luoxin.www.po.Admin;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class MysqlAdminImplTest {

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testAddIntoMysql() throws SQLException {
        String sql = "select * from admin where username = ? and password = ?";
        String a="a";
        String b="b";
        List<Admin> list=new ArrayList<>();
        MysqlAdmin mysqlAdmin=new MysqlAdminImpl();
        mysqlAdmin.addIntoMysql(sql,a,b);

    }

    @Test
    public void testSelectFormMysql() throws SQLException {
        String sql = "select * from admin ";
        String a="a";
        String b="b";
        List<Admin> list=new ArrayList<>();
        MysqlAdmin mysqlAdmin=new MysqlAdminImpl();
        list=mysqlAdmin.selectFormMysql(sql);
    }
}