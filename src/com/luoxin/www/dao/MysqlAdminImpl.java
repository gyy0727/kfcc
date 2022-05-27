package com.luoxin.www.dao;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Admin;
import com.luoxin.www.util.JDBCutil;
import com.luoxin.www.util.MyDataSoure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@IocAnnotation
public class MysqlAdminImpl implements MysqlAdmin{

    public Connection conn;
    public PreparedStatement pre;
    public ResultSet res;
    public Admin admin;


    public MysqlAdminImpl() throws SQLException {
    }

    @AopMethod
    @Override
    public void addIntoMysql(String sql, Object... args) throws SQLException {
        MyDataSoure dates=new MyDataSoure();
        conn = dates.getConnection();
        pre = conn.prepareStatement(sql);
        for(int i=0;i<args.length;i++){
            pre.setObject(i+1,args[i]);
        }
        pre.execute();
        JDBCutil.close(conn,pre,res);
    }
    @AopMethod
    @Override
    public List<Admin> selectFormMysql(String sql, Object... args) throws SQLException {
        List<Admin> list=new ArrayList<>();
        MyDataSoure dates=new MyDataSoure();
        conn = dates.getConnection();
        pre = conn.prepareStatement(sql);
        Admin acceptMysql=new Admin();

        for(int i=0;i<args.length;i++){
            pre.setObject(i+1,args[i]);
        }

        res = pre.executeQuery();
        if (res.next()) {
            acceptMysql.setAdminname(res.getString(1));
            acceptMysql.setPassword(res.getString(2));
            acceptMysql.setTelephone(res.getString(3));
            list.add(acceptMysql);
        }
        System.out.println("acceptMysql为"+acceptMysql);
        JDBCutil.close(conn,pre,res);
        return list;
    }
}
