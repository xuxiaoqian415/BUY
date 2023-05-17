package com.zust.buy.good.controller;

import com.zust.buy.common.entity.BigType;
import com.zust.buy.common.entity.PageBean;
import com.zust.buy.common.entity.Product;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.util.DateUtil;
import com.zust.buy.good.service.IIngredientService;
import com.zust.buy.good.service.IProductIngredientService;
import com.zust.buy.good.service.IProductService;
import com.zust.buy.good.service.IProductSwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IProductSwiperService productSwiperService;
    @Autowired
    private IProductIngredientService productIngredientService;
    @Autowired
    private IIngredientService ingredientService;

    @Value("${imagesFilePath.product}")
    private String productImagesPath;

    /**
     * 根据条件分页查询商品
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public ResponseData getSmallTypeList(@RequestBody PageBean pageBean) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", pageBean.getQuery());
        params.put("typeId", pageBean.getTypeId());
        params.put("start", pageBean.getStart());
        params.put("pageSize", pageBean.getPageSize());
        Map<String, Object> result = productService.getProductList(params);
        return ResponseData.ok(result);
    }

    /**
     * 更新首页轮播状态
     * @param id
     * @param isSwiper
     * @return
     */
    @RequestMapping("/update/{id}/swiper/{isSwiper}")
    public ResponseData updateSwiper(@PathVariable("id") Integer id, @PathVariable("isSwiper") Boolean isSwiper) {
        Product entity = new Product();
        entity.setId(id);
        entity.setIsSwiper(isSwiper);
        productService.updateById(entity);
        return ResponseData.ok();
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public ResponseData delete(@PathVariable("id") Integer id) {
        Product entity = new Product();
        entity.setId(id);
        entity.setDeleted(1);
        productService.updateById(entity);
        return ResponseData.ok();
    }

    /**
     * 上传商品图片
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadImage")
    public ResponseData uploadImage(MultipartFile file) {
        Map<String, Object> dataMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件名的后缀名
            String suffixName = file.getOriginalFilename().substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            File newFile = new File(productImagesPath + newFileName);
            try {
                file.transferTo(newFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            dataMap.put("imageName", newFileName);
            dataMap.put("src", "image/image/product/" + newFileName);
        }
        return ResponseData.ok(dataMap);
    }

    /**
     * 新增或修改商品
     * @param product
     * @return
     */
    @PostMapping("/save")
    public ResponseData save(@RequestBody Product product) {
        if (null == product.getId() || -1 == product.getId()) {
            productService.save(product);
        } else {
            product.setCreateTime(null);
            product.setUpdateTime(null);
            productService.updateById(product);
        }
        return ResponseData.ok();
    }
}
