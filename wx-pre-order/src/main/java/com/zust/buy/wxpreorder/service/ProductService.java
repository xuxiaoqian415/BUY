package com.zust.buy.wxpreorder.service;

import com.zust.buy.common.entity.BigType;
import com.zust.buy.common.entity.Product;
import com.zust.buy.common.entity.SmallType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("good-service")
public interface ProductService {

    @RequestMapping("/product/getSwiper")
    List<Product> getSwiper();

    @RequestMapping("/product/getHot")
    List<Product> getHot();

    @RequestMapping("/product/detail/{id}")
    Product getDetailById(@PathVariable("id") Integer id);

    @RequestMapping("/product/search")
    List<Product> search(String param);

    @RequestMapping("/type/bigType/list/{id}")
    List<SmallType> getBigTypeListById(@PathVariable("id") Integer bigTypeId);

    @RequestMapping("/type/bigType/list/All")
    List<BigType> getAllBigTypeList();
}
