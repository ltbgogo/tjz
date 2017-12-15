package com.abc.tjz.util.misc;

/**
 * @author LiuTongbin
 * @date 2017/12/10 0010 0:09
 */
public interface In3OutProcessor<IN1, IN2, IN3, OUT> {

    OUT process(IN1 in1, IN2 in2, IN3 in3) throws Exception;
}
