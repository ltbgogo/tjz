package com.couponkc;

import com.abc.tjz.App;
import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponCx;
import com.abc.tjz.entity.CouponKc;
import com.abc.tjz.fs.FileUtil;
import com.abc.tjz.util.misc.SpringManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.abc.tjz.repository.RepoFactory.rf;

/**
 * @author LiuTongbin
 * @date 2017/12/3 0003 19:42
 */
public class DbImporter {

    public static void main(String[] args) throws IOException {
        SpringManager.startMailApplication(App.class, args);
        for (String html : FileUtils.readLines(new File("F:\\tjz\\couponkc_fetch\\kc.har"), "UTF-8")) {
            if (html.trim().startsWith("\"text\": \"\\r\\n")) {
                html = html.replace("\\r\\n", "").replace("\\\"", "\"").trim();
                html = html.substring("\"text\": \"".length(), html.length() - 1);
                Document document = Jsoup.parse(html);
                for (Element li : document.body().children()) {
                    String thumbImagePath = li.select(".yhq-item-left-img img").attr("src");
                    String couponName = li.attr("data-title");
                    Integer couponPrice = null;
                    if (StringUtils.isNotBlank(li.attr("data-price"))) {
                        couponPrice = new BigDecimal(li.attr("data-price")).multiply(BigDecimal.TEN.multiply(BigDecimal.TEN)).intValue();
                    }
                    String expireTime = li.attr("data-date");
                    String detailImagePath = li.attr("data-img");
                    String site = li.attr("data-site")
                            .replace("hanbaowang", "hbw")
                            .replace("zhengongfu", "zgf");
                    CouponKc coupon = new CouponKc();
                    coupon.setCategory(rf.getCategoryRepo().findOne("couponkc" + site));
                    coupon.setCouponName(couponName);
                    coupon.setCouponPrice(couponPrice);
                    coupon.setDetailImagePath(detailImagePath);
                    coupon.setThumbImagePath(thumbImagePath);
                    coupon.setExpireTime(expireTime);
                    Matcher m = Pattern.compile("\\d+").matcher(li.attr("class"));
                    while (m.find()) {
                        coupon.getSubCategories().add(rf.getCategoryRepo().findOne("kfc" + m.group()));
                    }
                    rf.getCouponKcRepo().saveAndFlush(coupon);
                }
            }
        }
    }
}
