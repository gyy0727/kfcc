package com.luoxin.www.util;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.*;

public class MyDataSoureTest {

    @BeforeMethod
    public void setUp() {
    }
    @Test
    public void testGetConnection() throws SQLException {
        MyDataSoure myDataSoure=new MyDataSoure();
        myDataSoure.getConnection();
    }
}