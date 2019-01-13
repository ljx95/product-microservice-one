package com.yuanju.productserver.service;


import com.yuanju.productserver.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * author: LinjianXiong
 * Date: 2019/1/11
 * Time: 19:02
 */
public interface ProductService {

    /**
     * @Description  查询所有在架商品
     * @Author LJX
     * @Date 2019/1/12
     * @param
     * @return java.util.List<com.yuanju.productserver.dataobject.ProductInfo>
     */
    List<ProductInfo> findUpAll();

    /**
     * @Description  查询所有商品
     * @Author LJX
     * @Date 2019/1/12
     * @param pageable
     * @return org.springframework.data.domain.Page<com.yuanju.productserver.dataobject.ProductInfo>
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * @Description  通过productId获取商品信息
     * @Author LJX
     * @Date 2019/1/12
     * @param productId
     * @return com.yuanju.productserver.dataobject.ProductInfo
     */
    ProductInfo findOne(Integer productId);

    /**
     * @Description  保存商品
     * @Author LJX
     * @Date 2019/1/12
     * @param productInfo
     * @return com.yuanju.productserver.dataobject.ProductInfo
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * @Description 商品上架
     * @Author LJX
     * @Date 2019/1/12
     * @param productId
     * @return com.yuanju.productserver.dataobject.ProductInfo
     */
    ProductInfo onSale(Integer productId);

    /**
     * @Description  商品下架
     * @Author LJX
     * @Date 2019/1/12
     * @param productId
     * @return com.yuanju.productserver.dataobject.ProductInfo
     */
    ProductInfo offSale(Integer productId);



}