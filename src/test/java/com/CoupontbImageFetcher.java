package com;

import com.abc.tjz.fs.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuTongbin
 * @date 2017/11/25 0025 17:24
 */
public class CoupontbImageFetcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        int i = 0;
        for (File file : new File("F:\\tjz\\\\coupontb_fetch").listFiles()) {
            i++;
            JSONArray coupons = JSON.parseArray(FileUtils.readFileToString(file, "UTF-8"));
            for (Object o : coupons) {
                JSONObject json = (JSONObject) o;
                String path = FileUtil.download(((JSONObject) o).getString("article_pic"));
                System.out.println(((JSONObject) o).getString("article_pic"));
//                System.out.println(path);
                if (i % 20 == 0) {
                    TimeUnit.SECONDS.sleep(3);
                }
            }
        }
//        String s = FileUtils.readFileToString(new )
    }
}
