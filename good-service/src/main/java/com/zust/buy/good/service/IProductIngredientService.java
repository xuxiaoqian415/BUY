package com.zust.buy.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zust.buy.common.entity.Ingredient;
import com.zust.buy.common.entity.ProductIngredient;

import java.util.List;

/**
 * <p>
 * 菜品食材关系表 服务类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-14
 */
public interface IProductIngredientService extends IService<ProductIngredient> {

    List<ProductIngredient> listByProductId(Integer productId);

    void addNumber(ProductIngredient entity);

}
