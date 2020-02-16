package com.jcshang.jcrpc.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils used for reflection.
 *
 * @author Jcshang
 */

public class ReflectionUtils {

    /**
     * Creates an object of the given class.
     *
     * @param clazz The class to be created.
     * @param <T> Type of the class.
     * @return Created object of the given class.
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Get all public methods in the given class.
     * @param clazz Given class.
     * @return All public methods.
     */
    public static Method[] getPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> publicMethods = new ArrayList<>();
        for (Method m : methods) {
            if (Modifier.isPublic(m.getModifiers())) {
                publicMethods.add(m);
            }
        }

        return publicMethods.toArray(new Method[0]);
    }

    /**
     * Invoke the method in the designated object.
     * @param obj The given object.
     * @param method The method to be invoked.
     * @param args Arguments required by the method.
     * @return The return value of the invoked method.
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
