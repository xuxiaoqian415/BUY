package com.zust.buy.wxpreorder.controller;

import com.zust.buy.common.entity.BigType;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.entity.SmallType;
import com.zust.buy.wxpreorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  小类表 前端控制器
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-15
 */
@RestController
@RequestMapping("/wx")
public class GoodsTypeController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/smallType/list/{bigTypeId}")
    public ResponseData getListByBigTypeId(@PathVariable("bigTypeId") Integer bigTypeId) {
        List<SmallType> smallTypelist = productService.getBigTypeListById(bigTypeId);
        return ResponseData.ok(smallTypelist);
    }

    @RequestMapping("/bigType/getAll")
    public ResponseData getAll() {
        List<BigType> list = productService.getAllBigTypeList();
        return ResponseData.ok(list);
    }
}

