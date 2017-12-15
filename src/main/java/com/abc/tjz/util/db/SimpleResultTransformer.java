package com.abc.tjz.util.db;

import com.abc.tjz.util.misc.In2OutProcessor;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.hibernate.transform.ResultTransformer;

import java.util.List;

/**
 * @author LiuTongbin
 * @date 2017/12/9 0009 23:38
 */
public class SimpleResultTransformer implements ResultTransformer {

    private In2OutProcessor<Object[], String[], ?> resultMapper;

    @Override
    @SneakyThrows
    public Object transformTuple(Object[] tuple, String[] aliases) {
        if (resultMapper == null) {
            return tuple;
        } else {
            return resultMapper.process(tuple, aliases);
        }
    }

    @Override
    public List transformList(List collection) {
        return collection;
    }

    public SimpleResultTransformer(In2OutProcessor<Object[], String[], ?> resultMapper) {
        this.resultMapper = resultMapper;
    }
}
