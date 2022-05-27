package com.luoxin.www.dao;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Admin;
import com.luoxin.www.po.Goods;
import com.luoxin.www.util.JDBCutil;
import com.luoxin.www.util.MyDataSoure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@IocAnnotation
public class MysqlGoodImpl implements MysqlGood{
    public Connection conn;
    public PreparedStatement pre;
    public ResultSet res;



    @AopMethod
    @Override
    public List<Goods> selectFormMysql(String sql, Object... args) throws SQLException {
        List<Goods> list=new ArrayList<>();
        MyDataSoure dates=new MyDataSoure();
        conn = dates.getConnection();
        pre = conn.prepareStatement(sql);
        Goods acceptMysql=new Goods();
        for(int i=0;i<args.length;i++){
            pre.setObject(i+1,args[i]);
        }

        res = pre.executeQuery();
        if (res.next()) {
            acceptMysql.setRestaurantName(res.getString(1));
            acceptMysql.setName(res.getString(2));
            acceptMysql.setPrice(res.getString(3));
            list.add(acceptMysql);
        }
        System.out.println("acceptMysql为"+acceptMysql);
        JDBCutil.close(conn,pre,res);
        return list;
    }
}
