package com.luoxin.www.dao;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.po.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.util.JDBCutil;
import com.luoxin.www.util.MyDataSoure;
import org.testng.annotations.Test;

@IocAnnotation
public class MysqlUserImpl implements MysqlUser {
    public Connection conn;
    public PreparedStatement pre;
    public ResultSet res;


    //修改

    @AopMethod
    public void modifyFormMysql(String sql, Object... args) throws SQLException {
        MyDataSoure data=new MyDataSoure();
        conn = data.getConnection();
        pre = conn.prepareStatement(sql);
        for(int i=0;i<args.length;i++){
            pre.setObject(i+1,args[i]);
        }
        pre.execute();
        JDBCutil.close(conn,pre,res);

    }

    //查询
    @AopMethod
    public List<User> selectFormMysql(String sql, Object...args) throws SQLException {
        List<User> list=new ArrayList<>();
        MyDataSoure data=new MyDataSoure();
        conn = data.getConnection();
        pre = conn.prepareStatement(sql);
        User acceptMysql=new User();

        for(int i=0;i<args.length;i++){
            pre.setObject(i+1,args[i]);
        }

        res = pre.executeQuery();
        if (res.next()) {
            acceptMysql.setUsername(res.getString(1));
            acceptMysql.setPassword(res.getString(2));
            acceptMysql.setTelephone(res.getString(3));
            acceptMysql.setAddress(res.getString(4));
            list.add(acceptMysql);
        }
      System.out.println("acceptMysql为"+acceptMysql);
        JDBCutil.close(conn,pre,res);
        return list;

    }
}
