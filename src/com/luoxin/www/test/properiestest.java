package com.luoxin.www.test;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class properiestest{
    public static String driverClass;
    public static String url;
    public static String user;
    public static String password;
    public static Connection conn;

    static {
        try {
            InputStream is = com.luoxin.www.util.JDBCutil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties prop = new Properties();
            prop.load(is);
            driverClass = prop.getProperty("driverClass");
            user = prop.getProperty("user");
            url = prop.getProperty("url");
            password = prop.getProperty("password");
            Class.forName(driverClass);
        } catch (Exception e) {
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

    public static void close(Connection con, PreparedStatement pre, ResultSet res) {
        if (res != null) {
            try {
                res.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (pre != null) {


            try {
                pre.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (con != null) {
            try {
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

