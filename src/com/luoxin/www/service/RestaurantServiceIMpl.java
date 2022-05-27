package com.luoxin.www.service;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.controller.servlet.RequestMapping;
import com.luoxin.www.dao.MysqlRestaurant;
import com.luoxin.www.ioc.BeanFiled;
import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@IocAnnotation
public class RestaurantServiceIMpl implements RestaurantService{
    @BeanFiled
    public MysqlRestaurant mysqlRestaurant;
    public List<Restaurant> list;

    @Override
    @AopMethod
    @RequestMapping("setRestaurant")
    public void setRestaurant(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String sql="insert into restaurant(restaurantName,restaurantAddress,restaurantTelephone,restaurantMannagerName) values(?,?,?,?)";
        mysqlRestaurant.addIntoMysql(sql,req.getParameter("restaurantName"),req.getParameter("restaurantAddress"),req.getParameter("restaurantTelephone"),req.getParameter("restaurantMannagerName"));
    }
    @AopMethod
    @Override
    @RequestMapping("restaurantselectALL")
    public List<Restaurant> selectALL(HttpServletRequest req, HttpServletResponse resp, String name) throws ServletException, IOException, SQLException {
        String sql="select * from restaurant";
        list=mysqlRestaurant.selectFormMysql(sql);
        return list;
    }
}
