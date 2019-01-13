package com.yuanju.productserver.repository;

import com.yuanju.productserver.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: LinjianXiong
 * Date: 2019/1/11
 * Time: 18:53
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

    /**
     * @Description
     * @Author LJX
     * @Date 2019/1/11
     * @param productStatus
     * @return java.util.List<com.yuanju.productserver.dataobject.ProductInfo>
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
