package com.zust.buy.wxpreorder.controller;

import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.entity.Product;
import com.zust.buy.wxpreorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/wx/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("getSwiper")
    public ResponseData getSwiper() {
        List<Product> list = productService.getSwiper();
        return ResponseData.ok(list);
    }

    @RequestMapping("/getHot")
    public ResponseData getHot() {
        List<Product> list = productService.getHot();
        return ResponseData.ok(list);
    }

    @RequestMapping("/detail/{id}")
    public ResponseData getDetailById(@PathVariable("id") Integer id) {
        Product product = productService.getDetailById(id);
        return ResponseData.ok(product);
    }

    @RequestMapping("/search")
    public ResponseData search(@RequestParam("param") String param) {
        List<Product> list = productService.search(param);
        return ResponseData.ok(list);
    }

}

