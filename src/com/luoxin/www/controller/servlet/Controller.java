package com.luoxin.www.controller.servlet;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//修饰要用到的方法--请求转发器
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)


public @interface Controller {
    String value() default " ";
}
