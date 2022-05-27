package com.luoxin.www.service;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.controller.servlet.RequestMapping;
import com.luoxin.www.dao.*;
import com.luoxin.www.ioc.BeanFiled;
import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Admin;
import com.luoxin.www.po.Goods;
import com.luoxin.www.po.Order;
import com.luoxin.www.po.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@IocAnnotation
public class AdminServiceImpl implements AdminService{
    @BeanFiled
    private MysqlAdmin adminservice;
    @BeanFiled
    private MysqlOrder orderservice;
    @BeanFiled
    private MysqlGood goodservice;
    private List<Admin> listadmin;
    private List<Order> listorder;
    private Admin admin;

    @AopMethod
    @Override
    @RequestMapping("adminLogin")
    public void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String sql = "select * from admin where username = ? and password = ?";
        admin.setAdminname(req.getParameter("username"));
        admin.setPassword(req.getParameter("password"));
        System.out.println(admin);
        listadmin = adminservice.selectFormMysql(sql, admin.getAdminname(), admin.getPassword());
        System.out.println("adminlogin被调用");
        if (listadmin == null) {
            resp.getWriter().write("请输入正确用户名密码");
        } else {
            System.out.println("list为" + listadmin);
            req.setAttribute("adminlist", listadmin);
            req.getRequestDispatcher(" ").forward(req, resp);
        }
    }
    @AopMethod
    @Override
    @RequestMapping("adminRegister")
    public void adminRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        String sql = "insert into admin (username,password,telephone) values (?,?,?,?)";
        admin.setAdminname(req.getParameter("username"));
        admin.setTelephone(req.getParameter("telephone"));
        admin.setPassword(req.getParameter("password"));
        adminservice.addIntoMysql(sql, admin.getAdminname(), admin.getPassword(), admin.getTelephone());
        listadmin = this.SelectBuyadminname(admin.getAdminname());
        if (listadmin == null) {
            resp.getWriter().write("注册失败");
            req.getRequestDispatcher("登录页 ").forward(req, resp);
        }else {
            resp.getWriter().write("注册成功");
            req.getRequestDispatcher("登录页 ").forward(req, resp);
        }
    }

    @AopMethod
    @Override
    @RequestMapping("SelectBuyadminname")
    public List<Admin> SelectBuyadminname(String username) throws SQLException {
        String sql = "select * from admin where username = ?";
        listadmin = adminservice.selectFormMysql(sql, username);
        return listadmin;
    }
}
