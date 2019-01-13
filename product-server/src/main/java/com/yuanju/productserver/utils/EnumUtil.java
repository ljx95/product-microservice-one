package com.yuanju.productserver.utils;

import com.yuanju.productserver.enums.CodeEnum;

/**
 * author: LinjianXiong
 * Date: 2019/1/12
 * Time: 19:53
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
