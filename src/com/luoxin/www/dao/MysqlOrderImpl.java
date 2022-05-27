package com.luoxin.www.dao;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Order;
import com.luoxin.www.po.User;
import com.luoxin.www.util.JDBCutil;
import com.luoxin.www.util.MyDataSoure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@IocAnnotation
public class MysqlOrderImpl implements MysqlOrder {
    public Connection conn;
    public PreparedStatement pre;
    public ResultSet res;




    @AopMethod
    @Override
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
    @AopMethod
    @Override
    public List<Order> selectFormMysql(String sql, Object... args) throws SQLException {
        List<Order> list=new ArrayList<>();
        MyDataSoure data=new MyDataSoure();
        conn = data.getConnection();
        pre = conn.prepareStatement(sql);
        Order acceptMysql=new Order();
        for(int i=0;i<args.length;i++){
            pre.setObject(i+1,args[i]);
        }
        res = pre.executeQuery();
        if (res.next()) {
            acceptMysql.setSeller(res.getString(1));
            acceptMysql.setBuyers(res.getString(2));
            acceptMysql.setCommodity(res.getString(3));
            acceptMysql.setQuantity(res.getString(4));
            acceptMysql.setDate(res.getString(5));
            acceptMysql.setShippingAddress(res.getString(6));
            acceptMysql.setBuyersTelephone(res.getString(7));
            acceptMysql.setState(res.getString(8));
            list.add(acceptMysql);
        }
        System.out.println("acceptMysql为"+acceptMysql);
        JDBCutil.close(conn,pre,res);
        return list;
    }
}
