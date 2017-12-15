package com.couponcx;

import com.abc.tjz.App;
import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponCx;
import com.abc.tjz.entity.CouponWm;
import com.abc.tjz.fs.FileUtil;
import com.abc.tjz.util.misc.SpringManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.abc.tjz.repository.RepoFactory.rf;

/**
 * @author LiuTongbin
 * @date 2017/12/3 0003 19:42
 */
public class DbImporter {

    public static void main(String[] args) throws IOException {
        SpringManager.startMailApplication(App.class, args);
        for (String line : FileUtils.readLines(new File("F:\\tjz\\couponcx_fetch\\couponcx.har"), "UTF-8")) {
            if (line.trim().startsWith("\"text\": \"\\r\\n")) {
                String html = StringUtils.substringBetween(line, "\"text\": \"\\r\\n", "\\r\\n\"");
                html = html.replace("\\r\\n", "").replace("\\\"", "\"");

                Document document = Jsoup.parse(html);
                for (Element li : document.getElementsByTag("body").get(0).children()) {
                    if (li.tagName().equals("li")) {
                        Element head = li.child(0);
                        String thumbImagePath = head.getElementsByTag("img").attr("src");
                        Element body = li.child(1);
                        String couponName = body.child(0).text();
                        String tip = body.child(1).text();
                        Element addr = body.child(3);
                        int pagepreviews = NumberUtils.toInt(addr.child(0).text().split("：")[1]);
                        String detailLink = li.attr("data-url");
                        System.out.println(detailLink);
                        File detailFile = FileUtil.cache(detailLink);
                        Document detailDocument = Jsoup.parse(detailFile, "UTF-8");
                        Element detailBodyElement = detailDocument.getElementsByTag("body").get(0);
                        String detailImagePath = detailBodyElement.getElementsByClass("break-world").get(0).child(0).attr("src");
                        Element article = detailBodyElement.getElementsByTag("article").get(0);
                        String startTime = article.child(0).text().split("\\|")[1].trim();
                        Element detailElement = article.getElementsByClass("detail").get(0);
                        String summary = detailElement.getElementsMatchingOwnText("优惠简介|小编推荐")
                                .get(0).parent().nextElementSibling().html();
                        Optional<String> rules = detailElement.getElementsMatchingOwnText("使用说明|活动规则|活动说明|领券时间|领券说明").parallelStream()
                                .map((e) -> e.parent().nextElementSibling().html())
                                .reduce((a, b) -> a + b);

                        String link = detailBodyElement.getElementsByClass("footBtn1").get(0).attr("href");

                        Category category = null;
                        if (html.contains("xiaojukejiqmmcom"))
                            category = rf.getCategoryRepo().findOne("couponcx" + "dddc");
                        else if (html.contains("mobikeqmmcom"))
                            category = rf.getCategoryRepo().findOne("couponcx" + "mbdz");
                        else if (html.contains("ofoqmmso"))
                            category = rf.getCategoryRepo().findOne("couponcx" + "ofo");
                        else if (html.contains("01zhuancheqmmcom"))
                            category = rf.getCategoryRepo().findOne("couponcx" + "sqyc");
                        else if (html.contains("didizucheeqmmcom"))
                            category = rf.getCategoryRepo().findOne("couponcx" + "ddzc");
                        else if (html.contains("yongcheqmmcom"))
                            category = rf.getCategoryRepo().findOne("couponcx" + "ydyc");
                        else if (html.contains("10101111qmmcom"))
                            category = rf.getCategoryRepo().findOne("couponcx" + "szzc");
                        else if (html.contains("1haiqmmcn"))
                            category = rf.getCategoryRepo().findOne("couponcx" + "yhzc");
                        else if (html.contains("xqchuxingqmmcom"))
                            category = rf.getCategoryRepo().findOne("couponcx" + "xqdc");

                        CouponCx coupon = new CouponCx();
                        coupon.setCategory(category);
                        coupon.setCouponName(couponName);
                        coupon.setDetailImagePath(detailImagePath);
                        coupon.setLink(link);
                        coupon.setPageviews(pagepreviews);
                        coupon.setRules(rules.orElse(null));
                        coupon.setStartTime(startTime);
                        coupon.setSummary(summary);
                        coupon.setThumbImagePath(thumbImagePath);
                        coupon.setTip(tip);

                        rf.getCouponCxRepo().saveAndFlush(coupon);
                    }
                }
            }
        }
    }
}
