package com.luoxin.www.test;

import com.luoxin.www.dao.MysqlAdmin;
import com.luoxin.www.dao.MysqlAdminImpl;
import com.luoxin.www.dao.MysqlUser;
import com.luoxin.www.dao.MysqlUserImpl;
import com.luoxin.www.po.Admin;
import com.luoxin.www.po.User;
import com.luoxin.www.util.JDBCutil;
import com.luoxin.www.util.MyDataSoure;
import com.mysql.cj.protocol.Resultset;
import org.mariadb.jdbc.client.result.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTEST {
    public static void main(String[] args) throws SQLException {
        String sql = "select * from admin ";
        String a="a";
        String b="b";
        List<Admin> list=new ArrayList<>();
        MysqlAdmin mysqlUser=new MysqlAdminImpl();
        list=mysqlUser.selectFormMysql(sql);
        System.out.println(list);
        /*MyDataSoure myDataSoure=new MyDataSoure();
        int v=myDataSoure.getSize();
        System.out.println(v);*/


    }
}

