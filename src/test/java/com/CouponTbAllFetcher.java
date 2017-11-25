package com;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author LiuTongbin
 * @date 2017/11/25 0025 16:55
 */
public class CouponTbAllFetcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int category = 1; category < 12 ; category++) {
            for (int pageForm = 1; pageForm < 20; pageForm++) {
                File saveFile = FileUtils.getFile(new File("F:\\tjz\\coupontb_fetch"), category + "_" + pageForm);
                if (!saveFile.exists()) {
                    String url = "http://www.quanmama.com/ajax/wechat/superCoupon.ashx?" +
                            "action=getListByCategory&pageFrom=" + pageForm + "&pagesize=70&category=" + category;
                    System.out.println(url);
                    String s = IOUtils.toString(new URL(url), "UTF-8");
                    if (s.startsWith("{\"error")) {
                        break;
                    } else {
                        FileUtils.writeStringToFile(saveFile, s, "UTF-8");
                    }
                    TimeUnit.SECONDS.sleep(3);
                }
            }
        }
    }
}
