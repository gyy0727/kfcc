package com.luoxin.www.util;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;


public class LoggingImpl {
    public  static Logger logger=Logger.getLogger("com.luoxin.www.util.LoggingImpl");
    public LoggingImpl()  {

    }
    static {
        InputStream is = LoggingImpl.class.getClassLoader().getResourceAsStream("logging.properties");
        LogManager logManager = LogManager.getLogManager();
        try {
            logManager.readConfiguration(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test()
    {
        logger.info("logging.test();");
    }
}

