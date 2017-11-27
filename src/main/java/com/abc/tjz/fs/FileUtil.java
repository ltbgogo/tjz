package com.abc.tjz.fs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

/**
 * @author LiuTongbin
 * @date 2017/11/22 0022 23:51
 */
@Slf4j
public class FileUtil {

    public static String download(String url) throws IOException {
        try {
            String filePath = url.replaceFirst("https?://", "/images/");
            File file = FileUtils.getFile(FileConfig.of().getBaseDir(), filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                FileUtils.copyURLToFile(new URL(url), file);
            }
            return filePath;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static File cache(String url) throws IOException {
        try {
            String filePath = url.replaceFirst("https?://", "/cache/");
            File file = FileUtils.getFile(FileConfig.of().getBaseDir(), filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                FileUtils.copyURLToFile(new URL(url), file);
            }
            return file;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
