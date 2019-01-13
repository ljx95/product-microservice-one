package com.yuanju.productserver.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * author: LinjianXiong
 * Date: 2019/1/12
 * Time: 0:36
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryId;

    @JsonProperty("productInfoList")
    List<ProductInfoVO> productInfoVOList;
}
