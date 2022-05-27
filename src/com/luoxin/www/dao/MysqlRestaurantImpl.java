package com.luoxin.www.dao;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Order;
import com.luoxin.www.po.Restaurant;
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
public class MysqlRestaurantImpl implements MysqlRestaurant{
    public Connection conn;
    public PreparedStatement pre;
    public ResultSet res;



    @AopMethod
    @Override
    public void addIntoMysql(String sql, Object... args) throws SQLException {
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
    public List<Restaurant> selectFormMysql(String sql, Object... args) throws SQLException {
        List<Restaurant> list=new ArrayList<>();
        MyDataSoure data=new MyDataSoure();
        conn = data.getConnection();
        pre = conn.prepareStatement(sql);
        Restaurant acceptMysql=new Restaurant();
        for(int i=0;i<args.length;i++){
            pre.setObject(i+1,args[i]);
        }
        res = pre.executeQuery();
        if (res.next()) {
            acceptMysql.setRestaurantName(res.getString(1));
            acceptMysql.setRestaurantAddress(res.getString(2));
            acceptMysql.setRestaurantTelephone(res.getString(3));
            acceptMysql.setRestaurantMannagerName(res.getString(4));
        }
        System.out.println("acceptMysql为"+acceptMysql);
        JDBCutil.close(conn,pre,res);
        return list;
    }
}
