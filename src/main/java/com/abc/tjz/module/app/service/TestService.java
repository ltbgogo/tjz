package com.abc.tjz.module.app.service;

import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponCx;
import com.abc.tjz.repository.TestRepository;
import com.abc.tjz.util.db.JpaTemplate;
import com.abc.tjz.util.json.JsonUtil;
import com.abc.tjz.util.misc.SpringManager;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

import static com.abc.tjz.repository.RepoFactory.rf;

/**
 * @author LiuTongbin
 * @date 2017/12/2 0002 12:15
 */
@Service
public class TestService {

    public void addTest() {
        CouponCx c = rf.getCouponCxRepo().findOne("316642431113");
        System.out.println("****" + JsonUtil.toJson(c, true));
    }
}
