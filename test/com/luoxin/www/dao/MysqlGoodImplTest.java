package com.luoxin.www.dao;

import com.luoxin.www.po.Admin;
import com.luoxin.www.po.Goods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class MysqlGoodImplTest {

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testSelectFormMysql() throws SQLException {
        String sql = "select * from good ";
        String a="a";
        String b="b";
        List<Goods> list=new ArrayList<>();
        MysqlGood mysqlAdmin=new MysqlGoodImpl();
        list=mysqlAdmin.selectFormMysql(sql);
    }
}