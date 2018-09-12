package com.hnguigu.zshop.common.util;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-12 17:07
 **/
public class RandomUtil {

    public static int getRandNum(int min, int max) {
        int randNum = min + (int) (Math.random() * ((max - min) + 1));
        return randNum;
    }
}
