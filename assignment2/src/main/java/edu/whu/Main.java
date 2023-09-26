package edu.whu;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream input =
                     Main.class.getResourceAsStream("/myapp.properties")) {
            if (input == null) {return;}
            props.load(input);
            String className = props.getProperty("bootstrapClass");
            // 创建对象
            Object object =  createObject(className);

            // 检查是否有注解
            checkAnnotation(object);
        } catch (IOException e) {
            System.out.println("Load properties error!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    // 检查创建这个对象的类是否有方法包含@InitMethod注解，如果存在方法有此注解，则调用有这个注解的方法
    public static boolean checkAnnotation(Object object) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(InitMethod.class)){
                method.invoke(object);
                return true;
            }
        }
        return false;

    }


    // 利用反射根据获取的类名创建对象并返回
    public static Object createObject(String className) throws Exception {
        Class<?> userClass = Class.forName("edu.whu." +className);
        // 调用无参构造函数创建对象并返回
        return userClass.newInstance();
    }

}
