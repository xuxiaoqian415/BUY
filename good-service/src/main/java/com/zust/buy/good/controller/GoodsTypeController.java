package com.zust.buy.good.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zust.buy.common.entity.BigType;
import com.zust.buy.common.entity.Product;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.entity.SmallType;
import com.zust.buy.good.service.IBigTypeService;
import com.zust.buy.good.service.IProductService;
import com.zust.buy.good.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  商品类别 前端控制器
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-15
 */
@RestController
@RequestMapping("/type")
public class GoodsTypeController {

    @Autowired
    private ISmallTypeService smallTypeService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IBigTypeService bigTypeService;

    @ResponseBody
    @RequestMapping("/smallType/list/{id}")
    public List<SmallType> getListByBigTypeId(@PathVariable("id") Integer bigTypeId) {
        List<SmallType> smallTypelist = smallTypeService.list(new QueryWrapper<SmallType>()
                .eq("big_type_id", bigTypeId).eq("deleted", 0));
        for (SmallType smallType : smallTypelist) {
            List<Product> productList = productService.list(new QueryWrapper<Product>()
                    .eq("typeId", smallType.getId()).eq("deleted", 0));
            smallType.setProductList(productList);
        }
        return smallTypelist;
    }

    @ResponseBody
    @RequestMapping("/bigType/list/All")
    public List<BigType> getAll() {
        List<BigType> list = bigTypeService.list(new QueryWrapper<BigType>().eq("deleted", 0));
        return list;
    }
}

