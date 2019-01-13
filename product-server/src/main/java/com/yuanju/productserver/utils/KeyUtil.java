package com.yuanju.productserver.utils;

import java.util.Random;

/**
 * author: LinjianXiong
 * Date: 2019/1/12
 * Time: 22:29
 */
public class KeyUtil {

    /**
     * @Description
     *      生成唯一的主键
     *      格式: 时间+随机数
     * @Author LJX
     * @Date 2019/1/12
     * @param
     * @return java.lang.String
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
