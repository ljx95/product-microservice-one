package com.yuanju.productserver.service.impl;

import com.yuanju.productserver.dataobject.ProductInfo;
import com.yuanju.productserver.enums.ProductStatusEnum;
import com.yuanju.productserver.enums.ResultEnum;
import com.yuanju.productserver.exception.SellException;
import com.yuanju.productserver.repository.ProductInfoRepository;
import com.yuanju.productserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * author: LinjianXiong
 * Date: 2019/1/11
 * Time: 19:05
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo findOne(Integer productId) {
        return productInfoRepository.findById(productId).orElse(null);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo onSale(Integer productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);

        //商品不存在
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        // 商品已在架
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(Integer productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);

        //商品不存在
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        // 商品已在架
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }
}