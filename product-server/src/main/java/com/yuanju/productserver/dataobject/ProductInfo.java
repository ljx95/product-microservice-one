package com.yuanju.productserver.dataobject;

import com.yuanju.productserver.enums.ProductStatusEnum;
import com.yuanju.productserver.utils.EnumUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * author: LinjianXiong
 * Date: 2019/1/11
 * Time: 18:51
 */
@Data
@Entity
@Table(name = "tb_product")
public class ProductInfo {

    @Id
    private Integer id;
    /**
     * 商品名称
     */
    private String productName;

    /** 商品颜色*/
    private String productColor;

    /** 库存数量*/
    @Column( name = "product_sku")
    private int productSku;

    /** 库存状态*/
    @Column( name = "skustatus")
    private int skuStatus;

    /** 商品图片*/
    private String productPicture;

    /** 商品质量*/
    private String productQuality;

    /** 商品尺寸*/
    private double productSize;

    /** 商品重量*/
    private double productWeight;

    /** 商品卖点*/
    private String sellPoint;

    /** 商品特价*/
    private BigDecimal productRecommendprice;

    /** 商品销售价*/
    private BigDecimal productPrice;

    /** 商品描述*/
    private String productDefails;

    /** 限购数量*/
    private int limitNum;

    /** 商品类别编号*/
    @Column( name = "scategoryid")
    private Integer sCategoryId;

    /** 仓库编号*/
    @Column( name = "warehouseid")
    private Integer warehouseId;

    private Date createTime;

    private Date updateTime;

    /** 商品状态 1表示上架，0表示下架*/
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}

