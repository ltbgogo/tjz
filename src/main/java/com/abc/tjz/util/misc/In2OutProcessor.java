package com.abc.tjz.util.misc;

/**
 * @author LiuTongbin
 * @date 2017/12/10 0010 0:09
 */
public interface In2OutProcessor<IN1, IN2, OUT> {

    OUT process(IN1 in1, IN2 in2) throws Exception;
}
