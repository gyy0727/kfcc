package com.luoxin.www.util;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class MyDataSoure implements DataSource {
    private static List<Connection> pool = Collections.synchronizedList(new ArrayList<>());

    static {
        for (int i = 1; i <= 50; i++) {
            Connection conn = JDBCutil.getConnections();
            pool.add(conn);
        }
    }
    @Override
    public Connection getConnection() throws SQLException {
        Connection con;
        if (pool.size() > 0) {
            con = pool.remove(0);
        } else {
            throw new RuntimeException("连接数量已用尽");
        }
        return con;
    }
    public int getSize(){
        return pool.size();
    }

    @Override
    public Connection getConnection(String s, String s1) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int i) throws SQLException {

    }
    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }
}

