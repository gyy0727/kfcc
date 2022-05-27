package com.luoxin.www.service;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.controller.servlet.Controller;
import com.luoxin.www.controller.servlet.RequestMapping;
import com.luoxin.www.dao.MysqlUser;
import com.luoxin.www.po.Order;
import com.luoxin.www.po.User;
import com.luoxin.www.ioc.BeanFiled;
import com.luoxin.www.ioc.IocAnnotation;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller("UserServiceImpl")
@IocAnnotation
public class UserServiceImpl implements UserService {
    @BeanFiled
    private MysqlUser userservice;
    private List<User> listUser;
    private List<Order> listOrder;
    private User user;
    @BeanFiled
    private OrderService orderService;
    //登录
    @Override
    @RequestMapping("userlogin")
    @AopMethod
    public void userLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {

        /*String sql = "select * from customer where username = ? and password = ?";
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        System.out.println(user);
        listUser = userservice.selectFormMysql(sql, user.getUsername(), user.getPassword());

        if (listUser == null) {
            resp.getWriter().write("请输入正确用户名密码");
        } else {
            System.out.println("list为" + listUser);
            req.setAttribute("userlist", listUser);

        } */
        System.out.println("userlogin被调用");
    }

    //查询订单
    @AopMethod
    @Override
    @RequestMapping("orderSelect")
    public void orderSelect(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {

        String sql = "select * from order where buyers = ?";
        Order order=new Order();
        order.setBuyers(req.getParameter("username"));
        listOrder = userservice.selectFormMysql(sql,order.getBuyers());
        if (listUser == null) {
            resp.getWriter().write("查询不到订单");
            req.getRequestDispatcher("首页").forward(req, resp);
        } else {
            System.out.println("list为" + listOrder);
            req.setAttribute("Orderlist", listOrder);
            req.getRequestDispatcher("订单展示页").forward(req, resp);
        }
    }

    //修改个人信息
    @AopMethod
    @Override
    @RequestMapping("updateNew")
    public void updateNew(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {

        String sql="update customer set telephone=?,address=?, where username=?";
        user.setUsername(req.getParameter("username"));
        user.setTelephone(req.getParameter("telephone"));
        user.setAddress(req.getParameter("address"));
        userservice.modifyFormMysql(sql,user.getTelephone(),user.getAddress(),user.getUsername());
        //重定向到首页
        listUser=this.userSelectBuyUsername(user.getUsername());
        resp.getWriter().write("修改成功");
        /*
        *
        *
        *
        *
        *
        *
        * */
        req.getRequestDispatcher("首页 ").forward(req, resp);
    }


    //注册
    @Override
    @AopMethod
    @RequestMapping("userRegister")
    public void userRegister(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String sql = "insert into customer (username,password,telephone,address) values (?,?,?,?)";

        user.setAddress(req.getParameter("address"));
        user.setUsername(req.getParameter("username"));
        user.setTelephone(req.getParameter("telephone"));
        user.setPassword(req.getParameter("password"));
        userservice.modifyFormMysql(sql, user.getUsername(), user.getPassword(), user.getTelephone(), user.getAddress());
        listUser = this.userSelectBuyUsername(user.getUsername());
        if (listUser == null) {
            resp.getWriter().write("注册失败");
            req.getRequestDispatcher("登录页 ").forward(req, resp);
        }else {
            resp.getWriter().write("注册成功");
            req.getRequestDispatcher("登录页 ").forward(req, resp);
        }
    }

    //根据用户名查询用户
    @Override
    @AopMethod
    @RequestMapping("userSelectBuyUsername")
    public List<User> userSelectBuyUsername(String username) throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "select * from customer where username = ?";
        listUser = userservice.selectFormMysql(sql, username);
        return listUser;
    }

    //下单
    @Override
    @AopMethod
    @RequestMapping("placeOrder")
    public void placeOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        orderService.placeOrder(req,resp);
    }
}
