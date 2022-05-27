package com.luoxin.www.service;

import com.luoxin.www.po.Order;
import com.luoxin.www.po.User;
import com.luoxin.www.ioc.IocAnnotation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@IocAnnotation
public interface UserService {
    public void userLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException;
    public void orderSelect(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException;
    public void updateNew(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException;
    public void userRegister(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException;
    public List userSelectBuyUsername(String username) throws SQLException;
    public void placeOrder(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
}
