package com.zust.buy.good.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.buy.common.entity.Product;
import com.zust.buy.common.entity.SmallType;
import com.zust.buy.good.mapper.ProductMapper;
import com.zust.buy.good.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-14
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Map<String, Object> getProductList(Map<String, Object> params) {
        List<Product> productList = productMapper.getProductList(params);
        Integer total = productMapper.getTotal(params);
        Map<String, Object> result = new HashMap<>();
        result.put("productList", productList);
        result.put("total", total);
        return result;
    }

}
