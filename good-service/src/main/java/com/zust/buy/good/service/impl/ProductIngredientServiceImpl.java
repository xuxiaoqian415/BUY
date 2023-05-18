package com.zust.buy.good.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.buy.common.entity.Ingredient;
import com.zust.buy.common.entity.ProductIngredient;
import com.zust.buy.good.mapper.ProductIngredientMapper;
import com.zust.buy.good.service.IProductIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜品食材关系表 服务实现类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-14
 */
@Service
public class ProductIngredientServiceImpl extends ServiceImpl<ProductIngredientMapper, ProductIngredient> implements IProductIngredientService {

    @Autowired
    private ProductIngredientMapper productIngredientMapper;

    @Override
    public List<ProductIngredient> listByProductId(Integer productId) {
        return productIngredientMapper.listByProductId(productId);
    }

    @Override
    public void addNumber(ProductIngredient entity) {
        productIngredientMapper.addNumber(entity);
    }
}
