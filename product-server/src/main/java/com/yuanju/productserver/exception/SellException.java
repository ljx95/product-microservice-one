package com.yuanju.productserver.exception;

import com.yuanju.productserver.enums.ResultEnum;

/**
 * author: LinjianXiong
 * Date: 2019/1/12
 * Time: 19:35
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
