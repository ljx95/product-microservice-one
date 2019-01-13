package com.yuanju.productserver.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * author: LinjianXiong
 * Date: 2019/1/12
 * Time: 0:36
 */
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private Integer id;
    /**
     * 商品名称
     */
    @JsonProperty("name")
    private String productName;

    /** 商品图片*/
    @JsonProperty("picture")
    private String productPicture;

    /** 商品卖点*/
    @JsonProperty("sellPoint")
    private String sellPoint;

    /** 商品特价*/
    @JsonProperty("recommendPrice")
    private BigDecimal productRecommendprice;

    /** 商品销售价*/
    @JsonProperty("price")
    private BigDecimal productPrice;

    /** 商品描述*/
    @JsonProperty("description")
    private String productDefails;

}
