package com.qibenyu.explore.pluginable;

import java.lang.reflect.Field;

public class ReflectUtils {

    public static Field getField(Class clazz, String name) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    public static Object getField(Class clazz, Object object, String name)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(object);
    }

    public static void setField(Class clazz, Object target, String name, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = clazz.getDeclaredField(name);
        declaredField.setAccessible(true);
        declaredField.set(target, value);
    }
}
