package com.luoxin.www.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static com.luoxin.www.util.LoggingImpl.logger;


//实现动态代理接口
public class JDKImpl<T>  implements InvocationHandler {
    Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public <T> T getProxy(T ig) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("使用全局异常处理");
        try {
            Object result = method.invoke(target, objects);
        } catch (Exception e) {
            System.out.println("方法出现异常"+method.getName());
            logger.info("方法："+method.getName()+"出现了异常，为"+e);
        }
        return null;
    }
}






