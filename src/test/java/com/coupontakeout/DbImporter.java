package com.couponwm;

import com.abc.tjz.App;
import com.abc.tjz.entity.Category;
import com.abc.tjz.entity.CouponWm;
import com.abc.tjz.fs.FileUtil;
import com.abc.tjz.util.misc.SpringManager;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import static com.abc.tjz.repository.RepoFactory.rf;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuTongbin
 * @date 2017/11/25 0025 21:15
 */
public class DbImporter {

    public static void main(String[] args) throws IOException, InterruptedException {
//        setSysProxy("proxy3.bj.petrochina", "8080", "", "");
        SpringManager.startMailApplication(App.class, args);
        for (File file : new File("F:\\tjz\\couponwm_fetch").listFiles()) {
            String html = FileUtils.readFileToString(file, "UTF-8");
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

                    File detailFile = FileUtil.cache(detailLink);
                    Document detailDocument = Jsoup.parse(detailFile, "UTF-8");
                    Element detailBodyElement = detailDocument.getElementsByTag("body").get(0);
                    String detailImagePath = detailBodyElement.getElementsByClass("break-world").get(0).child(0).attr("src");
                    Element article = detailBodyElement.getElementsByTag("article").get(0);
                    String startTime = article.child(0).text().split("\\|")[1].trim();
                    Element detailElement = article.getElementsByClass("detail").get(0);
                    String summary = detailElement.getElementsContainingOwnText("优惠简介")
                            .get(0).parent().nextElementSibling().html();
                    Optional<String> rules = detailElement.getElementsMatchingOwnText("使用说明|活动规则|活动说明|领券时间|领券说明").parallelStream()
                            .map((e) -> e.parent().nextElementSibling().html())
                            .reduce((a, b) -> a + b);

                    String link = detailBodyElement.getElementsByClass("footBtn1").get(0).attr("href");
                    Category category = rf.getCategoryRepo().findOne("couponwm" + FilenameUtils.getBaseName(file.getName()));
                    CouponWm wm = new CouponWm();
                    wm.setCategory(category);
                    wm.setCouponName(couponName);
                    wm.setDetailImagePath(detailImagePath);
                    wm.setLink(link);
                    wm.setPageviews(pagepreviews);
                    wm.setRules(rules.orElse(null));
                    wm.setStartTime(startTime);
                    wm.setSummary(summary);
                    wm.setThumbImagePath(thumbImagePath);
                    wm.setTip(tip);
                    rf.getCouponWmRepo().saveAndFlush(wm);

//                    TimeUnit.SECONDS.sleep(1);
                }
            }
        }
    }

    private static String fetchDetail() throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod("http://m.quanmama.com/mzdm/2194565/extra.html");
        getMethod.setRequestHeader("", "");
        getMethod.setRequestHeader("Host", "m.quanmama.com");
        getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        getMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        getMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
        getMethod.setRequestHeader("Cookie", "UM_distinctid=15ff36b298b4-011a89dfa602808-173b7740-23820-15ff36b298c3c1; SERVERID=10e394bd8971e5f02d64355d446e6b11|1511711542|1511705363; amvid=f4ae13f9ec5fff67ef3ff14a7b986139; cn_1260889951_dplus=%7B%22distinct_id%22%3A%20%2215ff36b298b4-011a89dfa602808-173b7740-23820-15ff36b298c3c1%22%2C%22sp%22%3A%20%7B%22%24_sessionid%22%3A%200%2C%22%24_sessionTime%22%3A%201511708654%2C%22%24dp%22%3A%200%2C%22%24_sessionPVTime%22%3A%201511708654%2C%22%24recent_outside_referrer%22%3A%20%22%24direct%22%7D%2C%22initial_view_time%22%3A%20%221511616459%22%2C%22initial_referrer%22%3A%20%22http%3A%2F%2Fwww.quanmama.com%2Ft%2FwxTBCoupon%2FresultList.aspx%3FkeyValue%3D%25E9%25A5%25BC%25E5%25B9%25B2%22%2C%22initial_referrer_domain%22%3A%20%22www.quanmama.com%22%7D");
        getMethod.setRequestHeader("Connection", "keep-alive");
        getMethod.setRequestHeader("Upgrade-Insecure-Requests", "1");
        httpClient.executeMethod(getMethod);
        System.out.println(getMethod.getResponseBodyAsString());
        //释放连接
        getMethod.releaseConnection();
//        getMethod.setURI(new URL);

//        httpClient.executeMethod(HttpMethod)
        return null;
    }

    public static void setSysProxy(String host, String port, final String uname, final String pwd) {
        //It's not necessary for JDK versions below 1.3.
        System.getProperties().put("proxySet", true);
        System.getProperties().put("proxyHost", host);
        System.getProperties().put("proxyPort", port);
    }
}
