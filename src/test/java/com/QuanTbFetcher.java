package com;

import com.abc.tjz.App;
import com.abc.tjz.entity.QuanTb;
import com.abc.tjz.util.misc.SpringManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static com.abc.tjz.repository.RepoFactory.rf;

/**
 * @author LiuTongbin
 * @date 2017/11/19 0019 23:19
 */
public class QuanTbFetcher {

//    article_pic article_title article_oldPrice article_price article_buyFee article_coupon_click_url
    public static void main(String[] args) throws IOException {
        SpringManager.startMailApplication(App.class, args);
        String s = FileUtils.readFileToString(new File("d://test//tb.home.har"), "UTF-8");
        JSONObject json = JSON.parseObject(s);
        for (Object entryObject : json.getJSONObject("log").getJSONArray("entries")) {
            JSONObject entry = (JSONObject) entryObject;
            String responseText = entry.getJSONObject("response").getJSONObject("content").getString("text");
            if (responseText.startsWith("[{")) {
                JSONArray quans = JSON.parseArray(responseText);
                for (Object quanObject : quans) {
                    JSONObject quan = (JSONObject) quanObject;
                    String article_pic = quan.getString("article_pic");
                    String article_title = quan.getString("article_title");
                    String article_oldPrice = quan.getString("article_oldPrice").replace("￥", "");
                    String article_price = quan.getString("article_price").replace("￥", "");
                    String article_buyFee = quan.getString("article_buyFee").replace("￥", "");
                    String article_coupon_click_url = quan.getString("article_coupon_click_url");

                    QuanTb e = new QuanTb();
                    e.setImagePath(article_pic);
                    e.setTitle(article_title);
                    e.setOldPrice((int) (Float.parseFloat(article_oldPrice) * 100));
                    e.setQuanPrice((int) (Float.parseFloat(article_price) * 100));
                    e.setBuyPrice((int) (Float.parseFloat(article_buyFee) * 100));
                    e.setLink(article_coupon_click_url);
                    rf.getQuanTbRepo().saveAndFlush(e);
                }
            }
        }
    }
}
