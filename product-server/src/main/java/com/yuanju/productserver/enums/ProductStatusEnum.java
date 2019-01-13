package com.yuanju.productserver.enums;

import lombok.Getter;

/**
 * author: LinjianXiong
 * Date: 2019/1/11
 * Time: 19:08
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    DOWN(0, "下架"),
    UP(1, "在架"),
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
