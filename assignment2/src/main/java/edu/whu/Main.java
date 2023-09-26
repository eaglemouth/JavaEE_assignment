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
            Object object =  createObject(className);
            checkAnnotation(object);
        } catch (IOException e) {
            System.out.println("Load properties error!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

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

    public static Object createObject(String className) throws Exception {
        Class<?> userClass = Class.forName("edu.whu." +className);

        return userClass.newInstance();
    }

}
