package com.yuanju.productclient;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * author: LinjianXiong
 * Date: 2019/1/13
 * Time: 1:51
 */
@FeignClient(name = "product-microservice")
public interface ProductClient {
}
