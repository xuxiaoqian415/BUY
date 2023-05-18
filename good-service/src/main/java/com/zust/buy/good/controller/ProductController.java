package com.zust.buy.good.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.buy.common.entity.Product;
import com.zust.buy.common.entity.ProductSwiperImage;
import com.zust.buy.good.service.IProductService;
import com.zust.buy.good.service.IProductSwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-14
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IProductSwiperService productSwiperService;

    @ResponseBody
    @RequestMapping("/getSwiper")
    public List<Product> getSwiper() {
        List<Product> list = productService.list(new QueryWrapper<Product>()
                .eq("isSwiper", "1")
                .orderByAsc("swiperSort"));
        return list;
    }

    @ResponseBody
    @RequestMapping("/getHot")
    public List<Product> getHot() {
        Page<Product> page = new Page<>(0, 4);
        IPage<Product> pageProduct = productService.page(page, new QueryWrapper<Product>()
                .eq("isHot", "1").eq("deleted", 0)
                .orderByDesc("hotDateTime"));
        List<Product> list = pageProduct.getRecords();
        return list;
    }

    @ResponseBody
    @RequestMapping("/detail/{id}")
    public Product getDetailById(@PathVariable("id") Integer id) {
        Product product = productService.getById(id);
        String steps = product.getSteps();
        String s = new String(steps.getBytes(StandardCharsets.US_ASCII), StandardCharsets.UTF_8);

        List<ProductSwiperImage> swiperImageList = productSwiperService.list(new QueryWrapper<ProductSwiperImage>()
                .eq("productId", id).eq("deleted", 0)
                .orderByAsc("sort"));
        product.setSwiperImageList(swiperImageList);
        return product;
    }

    @ResponseBody
    @RequestMapping("/search")
    public List<Product> search(String param) {
        List<Product> list = productService.list(new QueryWrapper<Product>().like("name", param));
        return list;
    }

}

