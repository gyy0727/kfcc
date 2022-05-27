package com.luoxin.www.controller.servlet;

import com.luoxin.www.aop.AopMethod;
import com.luoxin.www.aop.ProxyImpl;
import com.luoxin.www.ioc.BeanFiled;
import com.luoxin.www.ioc.IocAnnotation;
import org.testng.annotations.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/d")
public class DispatcherServlet extends HttpServlet {
    private String filePath;
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();
    private Map<Class, Object> beanFactory = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        load();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = GetParam.getParameterList(req, resp);
        Method method = handlerMapping.get("/UserServiceImpl/userlogin");

        if (method == null) {
            resp.getWriter().write("404 NOT FOUND");
            return;
        }
        System.out.println(method.getDeclaringClass().getSimpleName());

        if (method.getAnnotation(AopMethod.class) == null) {

            try {
                method.invoke(method.getDeclaringClass().newInstance(), req, resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        } else {

            try {
                ProxyImpl.ProxyUtil(method, method.getDeclaringClass().getInterfaces(), method.getDeclaringClass().newInstance(), req, resp);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void load() {
        filePath = DispatcherServlet.class.getClassLoader().getResource("").getFile();
        loadOne(new File(filePath));
        assembleObject();
    }
//ioc初始化
    private void loadOne(File fileParent) {
        if (fileParent.isDirectory()) {
            File[] childrenFiles = fileParent.listFiles();
            if (childrenFiles == null || childrenFiles.length == 0) {
                return;
            }
            for (File child : childrenFiles) {
                if (child.isDirectory()) {
                    loadOne(child);
                } else {
                    String pathWithClass = child.getAbsolutePath().substring(filePath.length() - 1);
                    if (pathWithClass.contains(".class")) {
                        String fullName = pathWithClass.replaceAll("\\\\", ".").replace(".class", "");
                        try {
                            Class<?> aClass = Class.forName(fullName);
                            if (!aClass.isInterface()) {
                                IocAnnotation annotation = aClass.getAnnotation(IocAnnotation.class);
                                if (annotation != null) {
                                    Object instance = aClass.newInstance();
                                    if (aClass.getInterfaces().length > 0) {
                                        beanFactory.put(aClass.getInterfaces()[0], instance);
                                        System.out.println("正在加载【" + aClass.getInterfaces()[0] + "】,实例对象是：" + instance.getClass().getName());
                                    } else {
                                        beanFactory.put(aClass, instance);
                                    }
                                }
                            }
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        initHandlerMapping(beanFactory);

    }

    private void assembleObject() {
        for (Map.Entry<Class, Object> entry : beanFactory.entrySet()) {
            Object obj = entry.getValue();
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                BeanFiled annotation = field.getAnnotation(BeanFiled.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    try {
                        field.set(obj, beanFactory.get(field.getType()));
                        System.out.println("正在给【" + obj.getClass().getName() + "】属性【" + field.getName() + "】注入值【" + beanFactory.get(field.getType()).getClass().getName() + "】");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void initHandlerMapping(Map<Class, Object> bean) {
        if (bean.isEmpty()) {
            return ;
        }
        for (Map.Entry<Class, Object> entry : bean.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

            Controller controller = entry.getValue().getClass().getAnnotation(Controller.class);
            if (controller == null) {
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(Controller.class)) {
                Controller controller1 = clazz.getAnnotation(Controller.class);
                baseUrl = controller1.value();
            }
            for (Method method : clazz.getMethods()) {
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (requestMapping == null) {
                    continue;
                } else {
                    RequestMapping requestMapping1 = method.getAnnotation(RequestMapping.class);
                    String url = ("/" + baseUrl + "/" + requestMapping1.value()).replaceAll("/+", "/");
                    System.out.println("url为" + url + "      method为" + method.getName());
                    handlerMapping.put(url, method);
                }
            }
        }
    }
}