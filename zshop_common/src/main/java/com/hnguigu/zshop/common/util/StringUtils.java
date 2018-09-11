package com.hnguigu.zshop.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-11 21:08
 **/
public class StringUtils {

    public static String getFileName(String fileName) {
        int doIndex = fileName.lastIndexOf(".");
        String suffix = fileName.substring(doIndex);
        return UUID.randomUUID() + suffix;
    }
}
