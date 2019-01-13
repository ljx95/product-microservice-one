package com.yuanju.productserver.controller;

import com.yuanju.catalogclient.CategoryClient;
import com.yuanju.catalogcommon.CategoryOutput;
import com.yuanju.productserver.dataobject.ProductInfo;
import com.yuanju.productserver.exception.SellException;
import com.yuanju.productserver.form.ProductForm;
import com.yuanju.productserver.service.ProductService;
import com.yuanju.productserver.utils.KeyUtil;
import com.yuanju.productserver.utils.ResultVOUtil;
import com.yuanju.productserver.vo.ProductInfoVO;
import com.yuanju.productserver.vo.ProductVO;
import com.yuanju.productserver.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author: LinjianXiong
 * Date: 2019/1/11
 * Time: 19:10
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryClient categoryClient;

    /**
     * @Description  获取所有商品信息
     * @Author LJX
     * @Date 2019/1/12
     * @param page
     * @param size
     * @return com.yuanju.productserver.vo.ResultVO<com.yuanju.productserver.vo.ProductInfoVO>
     */
    @GetMapping("/list")
    public ResultVO<ProductInfoVO> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {

        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);

        //构造数据
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoPage) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
            }
         return ResultVOUtil.success(productInfoVOList);
    }

    /**
     * @Description  商品上架
     * @Author LJX
     * @Date 2019/1/12
     * @param productId
     * @param map
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") Integer productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * @Description  商品下架
     * @Author LJX
     * @Date 2019/1/12
     * @param productId
     * @param map
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") Integer productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sellproduct/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * @Description  进入添加商品页
     * @Author LJX
     * @Date 2019/1/12
     * @param productId
     * @param map
     * @return org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) Integer productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        //查询所有的类目
        List<CategoryOutput> categoryList = categoryClient.listForIsParent();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    /**
     * @Description  保存/更新商品信息
     * @Author LJX
     * @Date 2019/1/12 22:56
     * @param form
     * @param bindingResult
     * @param map
     * @return org.springframework.web.servlet.ModelAndView
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getId())) {
                productInfo = productService.findOne(form.getId());
            }

            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * @Description
     * 1.查询所有在架商品
     * 2.获取类别列表
     * 3.查询类别
     * 4.构造数据
     *
     * @Author LJX
     * @Date 2019/1/11
     * @param
     * @return com.ljx.productmicroservice.vo.ResultVO<com.ljx.productmicroservice.dataobject.ProductInfo>
     */
    @GetMapping("/lists")
    public ResultVO<ProductVO> list(){
        //1.获取所有在架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2.获取类别列表
        //2. 获取类目type列表
        List<Integer> categoryIdList = productInfoList.stream()
                .map(ProductInfo::getSCategoryId)
                .collect(Collectors.toList());

        // 3.查询类别
        List<CategoryOutput> categoryList = categoryClient.listForIds(categoryIdList);

        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (CategoryOutput categoryOutput: categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(categoryOutput.getCategoryName());
            productVO.setCategoryId(categoryOutput.getCategoryId());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getSCategoryId().equals(categoryOutput.getCategoryId())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

    @GetMapping("/getString")
    public String getString(){
        List<Integer> list =new ArrayList<Integer>();
        list.add(1);
        List<CategoryOutput> c = categoryClient.listForIds(list);
        return c.get(0).getCategoryName();
    }
}
