package com.luoxin.www.util;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class JDBCutilTest {
    public Connection conn;
    public PreparedStatement pre;
    public ResultSet res;
    @BeforeMethod
    public void setUp() {

    }

    @Test
    public void testGetConnections() {
        JDBCutil.getConnections();
    }

    @Test
    public void testClose() throws SQLException {
        JDBCutil.close(conn,pre,res);
    }
}