package com.abc.tjz.util.db;

import com.abc.tjz.util.misc.In2OutProcessor;
import com.abc.tjz.util.misc.ReflectUtil;
import com.alibaba.fastjson.JSONObject;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author LiuTongbin
 * @date 2017/12/9 0009 23:08
 */
public class DBUtil {

    private static final Map<String, List<Selection<?>>> SELECTION_CACHE = Collections.synchronizedMap(new HashMap<>());
    public static <D> List<Selection<?>> getSelections(Root<D> root, Class<D> entityType, boolean isFull) {
        if (SELECTION_CACHE.get(entityType.getName() + "_" + isFull) == null) {
            List<Selection<?>> selections = new ArrayList<>();
            for (Class clazz : ReflectUtil.getSuperClassesAndSelf(entityType)) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Column.class) || (isFull && (field.isAnnotationPresent(Lob.class)))) {
                        selections.add(root.get(field.getName()).alias(field.getName()));
                    } else if (field.isAnnotationPresent(ManyToOne.class)) {
                        selections.add(root.get(field.getName()).get("id").alias(field.getName() + "Id"));
                    }
                }
            }
            SELECTION_CACHE.put(entityType.getName() + "_" + isFull, selections);
        }
        return SELECTION_CACHE.get(entityType.getName() + "_" + isFull);
    }


}
