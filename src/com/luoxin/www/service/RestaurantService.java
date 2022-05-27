package com.luoxin.www.service;

import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@IocAnnotation
public interface RestaurantService {
    public void setRestaurant(HttpServletRequest req, HttpServletResponse resp) throws SQLException;
    public List selectALL(HttpServletRequest req, HttpServletResponse resp, String name) throws ServletException, IOException, SQLException;

}
