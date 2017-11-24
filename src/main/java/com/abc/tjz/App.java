package com.abc.tjz;

import com.abc.tjz.entity.CouponTb;
import com.abc.tjz.util.misc.SpringManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

import static com.abc.tjz.repository.RepoFactory.rf;

@SpringBootApplication
public class App {
	
    public static void main( String[] args ) throws IOException {
		SpringApplication.run(App.class, args);
//    	SpringManager.startMailApplication(App.class, args);
    }
}
