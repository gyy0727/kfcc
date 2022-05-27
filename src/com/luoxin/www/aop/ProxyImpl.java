package com.luoxin.www.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//实现动态代理
public class ProxyImpl {
    public ProxyImpl() throws NoSuchMethodException {
    }
    public static synchronized void ProxyUtil(Method method, Object obj, Object obje, HttpServletRequest req, HttpServletResponse resp) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class clazz = obj.getClass();
        JDKImpl jdk = new JDKImpl();
        jdk.setTarget(obje);
        Object objec = jdk.getProxy(obj);
        Method method1=objec.getClass().getMethod(method.getName(),HttpServletRequest.class,HttpServletResponse.class);
        method1.invoke(objec,req,resp);
    }

}


