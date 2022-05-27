package com.luoxin.www.service;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.controller.servlet.RequestMapping;
import com.luoxin.www.dao.MysqlOrder;
import com.luoxin.www.ioc.BeanFiled;
import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Admin;
import com.luoxin.www.po.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



@IocAnnotation
public class OrderServiceImpl implements OrderService{
    @BeanFiled
    public MysqlOrder mysqlOrder;
    public  List<Order> listorder;
    public Order order;
    @Override
    @AopMethod
    @RequestMapping("OrderselectALL")
    public List<Order> selectALL(HttpServletRequest req, HttpServletResponse resp,String name) throws ServletException, IOException, SQLException {
        String sql="select * from order where seller = ?";
        listorder=mysqlOrder.selectFormMysql(sql,name);
        req.setAttribute(name+"list", listorder);
        return listorder;
    }

    @Override
    @AopMethod
    @RequestMapping("placeOrder")
    public void placeOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String sql="insert into order(seller,buyers,commodity,quantity,date,shippingAdress,buyersTelephone,state) values(?,?,?,?,?,?,?,?)";
        order.setSeller(req.getParameter("seller"));
        order.setBuyers(req.getParameter("buyers"));
        order.setCommodity(req.getParameter("commodity"));
        order.setQuantity(req.getParameter("quantity"));
        order.setDate(req.getParameter("date"));
        order.setShippingAddress(req.getParameter("shippingAdress"));
        order.setBuyersTelephone(req.getParameter("buyerstelephone"));
        order.setState("未完成");
        mysqlOrder.modifyFormMysql(sql,order.getSeller(),order.getBuyers(),order.getCommodity(),order.getQuantity(),order.getDate(),order.getShippingAddress(),order.getBuyersTelephone(),order.getState());

    }

    @Override
    @AopMethod
    @RequestMapping("completeOrder")
    public void completeOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String sql="update order set state ='已完成' where seller=? ";
        mysqlOrder.modifyFormMysql(sql,req.getParameter("seller"));
    }
}
