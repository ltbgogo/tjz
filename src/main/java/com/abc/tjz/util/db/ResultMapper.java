package com.abc.tjz.util.db;

import com.abc.tjz.util.misc.In2OutProcessor;
import com.abc.tjz.util.misc.Val;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author LiuTongbin
 * @date 2017/12/10 0010 8:52
 */
public class ResultMapper {

    public static final In2OutProcessor<Object[], String[], JSONObject> ofFastJson() {
        return (values, aliases) -> {
            JSONObject map = new JSONObject();
            for (int i = 0; i < values.length; i++) {
                map.put(aliases[i], values[i]);
            }
            return map;
        };
    }

    public static final In2OutProcessor<Object[], String[], JSONObject> ofFastJson(String[] realAliases) {
        return (values, aliases) -> {
            JSONObject map = new JSONObject();
            for (int i = 0; i < values.length; i++) {
                map.put(realAliases[i], values[i]);
            }
            return map;
        };
    }

    public static final In2OutProcessor<Object[], String[], JSONObject> ofFastJsonLower() {
        Val<String[]> realAlias = Val.of();
        return (values, aliases) -> {
            if (realAlias.getValue() == null) {
                realAlias.setValue(Arrays.stream(aliases).map(String::toLowerCase).toArray(String[]::new));
            }
            JSONObject map = new JSONObject();
            for (int i = 0; i < values.length; i++) {
                map.put(realAlias.getValue()[i], values[i]);
            }
            return map;
        };
    }

    public static final In2OutProcessor<Object[], String[], JSONObject> ofFastJsonCamel() {
        Val<String[]> realAlias = Val.of();
        return (values, aliases) -> {
            if (realAlias.getValue() == null) {
                realAlias.setValue(Arrays.stream(aliases).map(a -> StringUtils.uncapitalize(Arrays.stream(a.toLowerCase().split("_")).map(StringUtils::capitalize).reduce((s1, s2) -> s1 + s2).get())).toArray(String[]::new));
            }
            JSONObject map = new JSONObject();
            for (int i = 0; i < values.length; i++) {
                map.put(realAlias.getValue()[i], values[i]);
            }
            return map;
        };
    }
}
