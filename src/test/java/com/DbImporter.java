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
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.abc.tjz.repository.RepoFactory.rf;

/**
 * @author LiuTongbin
 * @date 2017/11/25 0025 21:15
 */
public class DbImporter {

    public static void main(String[] args) throws IOException {
        SpringManager.startMailApplication(App.class, args);
        for (File file : new File("F:\\tjz\\\\coupontb_fetch").listFiles()) {
            JSONArray coupons = JSON.parseArray(FileUtils.readFileToString(file, "UTF-8"));
            for (Object o : coupons) {
                JSONObject article = (JSONObject) o;
                String article_pic = article.getString("article_pic");
                String article_title = article.getString("article_title");
                String article_oldPrice = article.getString("article_oldPrice").replace("￥", "");
                String article_price = article.getString("article_price").replace("￥", "");
                String article_buyFee = article.getString("article_buyFee").replace("￥", "");
                String article_coupon_click_url = article.getString("article_coupon_click_url");

                CouponTb couponTb = new CouponTb();
                couponTb.setLink(article_coupon_click_url);
                couponTb.setBuyPrice(new BigDecimal(article_buyFee).multiply(new BigDecimal("100")).intValue());
                couponTb.setCouponPrice(new BigDecimal(article_price).multiply(new BigDecimal("100")).intValue());
                couponTb.setOldPrice(new BigDecimal(article_oldPrice).multiply(new BigDecimal("100")).intValue());
                couponTb.setGoodsName(article_title);
                couponTb.setImagePath(article_pic);
                couponTb.setBuyerCount(Math.max(12652, new Random(30000).nextInt()));
                if (file.getName().startsWith("1_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbfood"));
                } else if (file.getName().startsWith("2_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbtextile"));
                } else if (file.getName().startsWith("3_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbappliances"));
                } else if (file.getName().startsWith("4_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbwomenswear"));
                } else if (file.getName().startsWith("5_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbbeauty"));
                } else if (file.getName().startsWith("6_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbfurniture"));
                } else if (file.getName().startsWith("7_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbbaby"));
                } else if (file.getName().startsWith("8_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbacc"));
                } else if (file.getName().startsWith("9_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbsport"));
                } else if (file.getName().startsWith("10_")) {
                    couponTb.setCategory(rf.getCategoryRepo().findOne("juantbmenswear"));
                }

                rf.getCouponTbRepo().saveAndFlush(couponTb);
            }
        }
    }
}
