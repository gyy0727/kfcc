package com.luoxin.www.service;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.controller.servlet.RequestMapping;
import com.luoxin.www.dao.MysqlGood;
import com.luoxin.www.dao.MysqlGoodImpl;
import com.luoxin.www.dao.MysqlRestaurant;
import com.luoxin.www.ioc.BeanFiled;
import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Goods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@IocAnnotation
public class GoodsServicesImpl implements GoodsService{
    public Goods goods;
    public List<Goods> listgood;
    @BeanFiled
    public MysqlGood mysqlGood;
    @Override
    @AopMethod
    @RequestMapping("GoodselectALL")
    public List<Goods> selectALL(HttpServletRequest req, HttpServletResponse resp, String name) throws ServletException, IOException, SQLException {
        String sql="select * from good where restaurantName=?";
        goods.setRestaurantName(req.getParameter("restaurantName"));
        listgood=mysqlGood.selectFormMysql(sql,goods.getRestaurantName());
        return listgood;

    }
}
