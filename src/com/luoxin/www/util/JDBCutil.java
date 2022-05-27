package com.luoxin.www.util;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCutil {
    public static String driverClass;
    public static String url;
    public static String user;
    public static String password;
    public static Connection conn;

    private JDBCutil() {

    }

    static {

            InputStream is = JDBCutil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driverClass = prop.getProperty("driverClass");
            user = prop.getProperty("user");
            url = prop.getProperty("url");
            password = prop.getProperty("password");
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnections() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection con, PreparedStatement pre, ResultSet res) throws SQLException {
        if (res != null) {
                res.close();
        }
        if (pre != null) {
            pre.close();
        }
        if (con != null) {
            con.close();

        }
    }
}

