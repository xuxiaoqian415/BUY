package com.zust.buy.good.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.buy.common.entity.Ingredient;
import com.zust.buy.common.entity.ProductIngredient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜品食材关系表 Mapper 接口
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-14
 */
@Mapper
public interface ProductIngredientMapper extends BaseMapper<ProductIngredient> {

    List<ProductIngredient> listByProductId(@Param("productId") Integer productId);

    void addNumber(ProductIngredient entity);

}
