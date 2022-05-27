package com.luoxin.www.service;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class UserServiceImplTest {
    @BeforeMethod
    public void setUp() {
    }
    @Test
    public void testUserLogin() throws ServletException, SQLException, IOException {
        UserServiceImpl userService=new UserServiceImpl();

    }

    @Test
    public void testOrderSelect() {
    }

    @Test
    public void testUpdateNew() {
    }

    @Test
    public void testUserRegister() {
    }

    @Test
    public void testUserSelectBuyUsername() {
    }

    @Test
    public void testPlaceOrder() {
    }
}