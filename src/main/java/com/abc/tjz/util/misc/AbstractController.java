package com.abc.tjz.util.misc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuTongbin
 * @date 2017/11/18 0018 23:22
 */
public class AbstractController {

    protected String getViewRoot() {
        return this.getClass().getAnnotation(RequestMapping.class).value()[0].replaceFirst("/action", "");
    }
}
