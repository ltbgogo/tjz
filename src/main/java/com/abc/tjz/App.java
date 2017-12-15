package com.abc.tjz;

import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponCx;
import com.abc.tjz.fs.FileUtil;
import com.abc.tjz.module.app.service.TestService;
import com.abc.tjz.util.db.DBUtil;
import com.abc.tjz.util.db.JpaTemplate;
import com.abc.tjz.util.db.ResultMapper;
import com.abc.tjz.util.db.entity.IdEntity;
import com.abc.tjz.util.json.JsonUtil;
import com.abc.tjz.util.misc.SpringManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.abc.tjz.repository.RepoFactory.rf;

@SpringBootApplication
public class App {
	
    public static void main( String[] args ) throws IOException, IllegalAccessException, NoSuchFieldException {
		SpringApplication.run(App.class, args);
//    	SpringManager.startMailApplication(App.class, args);
//    	SpringManager.getBean(TestService.class).addTest();
    }
}
