package com.abc.tjz.util.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author LiuTongbin
 * @date 2017/12/9 0009 23:59
 */
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(staticName = "of")
public class Val<T> {

    private T value;
}
