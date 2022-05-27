package com.luoxin.www.service;
import com.luoxin.www.ioc.IocAnnotation;
import com.luoxin.www.po.Admin;
import com.luoxin.www.po.Order;
import com.luoxin.www.po.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@IocAnnotation
public interface AdminService {
    public void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException;
    public void adminRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException;
    public List<Admin> SelectBuyadminname(String username) throws SQLException ;
}
