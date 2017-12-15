package com.abc.tjz.util.misc;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author LiuTongbin
 * @date 2017/12/10 0010 1:26
 */
public class ReflectUtil {

    public static List<Class> getSuperClasses(Class clazz) {
        List<Class> superClasses = new ArrayList<>();
        while ((clazz = clazz.getSuperclass()) != null) {
            superClasses.add(clazz);
        }
        Collections.reverse(superClasses);
        return superClasses;
    }

    public static List<Class> getSuperClassesAndSelf(Class clazz) {
        List<Class> classes = getSuperClasses(clazz);
        classes.add(clazz);
        return classes;
    }
}
