package com.abc.tjz.fs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.File;

/**
 * @author LiuTongbin
 * @date 2017/11/23 0023 0:10
 */
@NoArgsConstructor
@Getter
public class FileConfig {

    private File baseDir = new File("F:\\tjz\\fileStorage");

    @Getter
    @Accessors(fluent = true)
    private static FileConfig of = new FileConfig();
}
