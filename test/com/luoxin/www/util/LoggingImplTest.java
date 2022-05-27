package com.luoxin.www.util;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoggingImplTest {

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testTest1() {
        LoggingImpl logging=new LoggingImpl();
        logging.test();
    }
}
