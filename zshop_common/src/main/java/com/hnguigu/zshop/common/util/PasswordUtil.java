package com.hnguigu.zshop.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-05 21:33
 **/
public class PasswordUtil {

    public static final String md5(String password, String salt) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //密码
        Object source = password;
        //加密次数
        int hashIterations = 1024;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        return result.toString();
    }
}
