package com.luoxin.www.aop;

import com.luoxin.www.ioc.BeanFiled;
import com.luoxin.www.ioc.Ioc;
import com.luoxin.www.ioc.IocAnnotation;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AopImpl <T> {
    private HashMap<Class, Object> beanFactory = new HashMap<>();
    private String filePath;

    public void load() {
        filePath = Ioc.class.getClassLoader().getResource("").getFile();
        loadOne(new File(filePath));
        assembleObject();
    }

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
                                        System.out.println("正在加载【" + aClass.getInterfaces()[0] + "】,实例对象是：" + instance.getClass().getName());
                                        beanFactory.put(aClass.getInterfaces()[0], instance);
                                    } else {
                                        System.out.println("正在加载【" + aClass.getName() + "】,实例对象是：" + instance.getClass().getName());
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
                        System.out.println("正在给【" + obj.getClass().getName() + "】属性【" + field.getName() + "】注入值【" + beanFactory.get(field.getType()).getClass().getName() + "】");
                        field.set(obj, beanFactory.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}

