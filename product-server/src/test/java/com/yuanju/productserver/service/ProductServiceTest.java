package com.yuanju.productserver.service;

import com.yuanju.productserver.ProductServerApplicationTests;
import com.yuanju.productserver.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

/**
 * author: LinjianXiong
 * Date: 2019/1/11
 * Time: 19:12
 */
@Component
public class ProductServiceTest extends ProductServerApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception{
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }
}