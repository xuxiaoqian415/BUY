package com.zust.buy.good.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zust.buy.common.entity.*;
import com.zust.buy.common.util.DateUtil;
import com.zust.buy.good.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IProductSwiperService productSwiperService;
    @Autowired
    private ISmallTypeService smallTypeService;

    @Value("${imagesFilePath.product}")
    private String productImagesPath;
    @Value("${imagesFilePath.swiper}")
    private String swiperImagesPath;
    @Value("${imagesFilePath.product-swiper}")
    private String productSwiperPath;

    /**
     * 根据条件分页查询商品
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public ResponseData getProductList(@RequestBody PageBean pageBean) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", pageBean.getQuery());
        params.put("typeId", pageBean.getTypeId());
        params.put("isSwiper", pageBean.getIsSwiper());
        params.put("start", pageBean.getStart());
        params.put("pageSize", pageBean.getPageSize());
        Map<String, Object> result = productService.getProductList(params);
        return ResponseData.ok(result);
    }

    /**
     * 更新商品热卖状态
     * @param id
     * @param isHot
     * @return
     */
    @RequestMapping("/update/{id}/hot/{isHot}")
    public ResponseData updateHot(@PathVariable("id") Integer id, @PathVariable("isHot") Boolean isHot) {
        Product entity = new Product();
        entity.setId(id);
        entity.setIsHot(isHot);
        productService.updateById(entity);
        return ResponseData.ok();
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
    @RequestMapping("/uploadImage/{type}")
    public ResponseData uploadImage(MultipartFile file, @PathVariable("type") Integer type) {
        Map<String, Object> dataMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件名的后缀名
            String suffixName = file.getOriginalFilename().substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            File newFile;
            dataMap.put("imageName", newFileName);
            if (type == 1) { // 1商品图
                newFile = new File(productImagesPath + newFileName);
                dataMap.put("src", "image/image/product/" + newFileName);
            } else if (type == 2){ // 2首页轮播图
                newFile = new File(swiperImagesPath + newFileName);
                dataMap.put("src", "image/image/swiper/" + newFileName);
            } else { // 3详情页轮播图
                newFile = new File(productSwiperPath + newFileName);
                dataMap.put("src", "image/image/productSwiper/" + newFileName);
            }
            try {
                file.transferTo(newFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
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

    /**
     * 获取详情页轮播图列表
     * @param productId
     * @return
     */
    @RequestMapping("/detailSwiperList/{id}")
    public ResponseData getDetailSwiperList(@PathVariable("id") Integer productId) {
        List<ProductSwiperImage> result = productSwiperService.list(new QueryWrapper<ProductSwiperImage>()
                .eq("productId", productId).orderByAsc("sort"));
        return ResponseData.ok(result);
    }

    /**
     * 添加详情页轮播图
     * @param swiper
     * @return
     */
    @PostMapping("/add/detailSwiper")
    public ResponseData addDetailSwiper(@RequestBody ProductSwiperImage swiper) {
        productSwiperService.save(swiper);
        return ResponseData.ok();
    }

    @RequestMapping("/delete/detailSwiper/{id}")
    public ResponseData deleteDetailSwiper(@PathVariable("id") Integer id) {
        productSwiperService.removeById(id);
        return ResponseData.ok();
    }

    @RequestMapping("/detail/{id}")
    public ResponseData getDetail(@PathVariable("id") Integer id) {
        Product product = productService.getById(id);
        product.setBigTypeId(smallTypeService.getById(product.getTypeId()).getBigTypeId());
        return ResponseData.ok(product);
    }
}
