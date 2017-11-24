package com;

import com.abc.tjz.App;
import com.abc.tjz.entity.CouponTb;
import com.abc.tjz.fs.FileUtil;
import com.abc.tjz.util.misc.SpringManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.abc.tjz.repository.RepoFactory.rf;

/**
 * @author LiuTongbin
 * @date 2017/11/19 0019 23:19
 */
public class CouponTbFetcher {

//    article_pic article_title article_oldPrice article_price article_buyFee article_coupon_click_url
    public static void main(String[] args) throws IOException {
        SpringManager.startMailApplication(App.class, args);
        JSONObject object = JSON.parseObject(FileUtils.readFileToString(new File("d://test//tb.home.har"), "UTF-8")).getJSONObject("log");
        for (Object entryObject : object.getJSONArray("entries")) {
            JSONObject entry = (JSONObject) entryObject;
            String text = entry.getJSONObject("response").getJSONObject("content").getString("text");
            if (text.startsWith("[")) {
                for (Object articleObject : JSON.parseArray(text)) {
                    JSONObject article = (JSONObject) articleObject;

                    String article_pic = article.getString("article_pic");
                    String article_title = article.getString("article_title");
                    String article_oldPrice = article.getString("article_oldPrice").replace("￥", "");
                    String article_price = article.getString("article_price").replace("￥", "");
                    String article_buyFee = article.getString("article_buyFee").replace("￥", "");
                    String article_coupon_click_url = article.getString("article_coupon_click_url");

                    CouponTb couponTb = new CouponTb();
                    couponTb.setLink(article_coupon_click_url);
                    couponTb.setBuyPrice((int) (Float.parseFloat(article_buyFee) * 100));
                    couponTb.setCouponPrice((int) (Float.parseFloat(article_price) * 100));
                    couponTb.setOldPrice((int) (Float.parseFloat(article_oldPrice) * 100));
                    couponTb.setTitle(article_title);
                    String imagePath = FileUtil.download(article_pic);
                    couponTb.setImagePath(imagePath);

                    rf.getCouponTbRepo().saveAndFlush(couponTb);
                }
            }
        }
    }
}
