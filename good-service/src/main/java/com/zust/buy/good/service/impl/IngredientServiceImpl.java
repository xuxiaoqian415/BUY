package com.zust.buy.good.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.buy.common.entity.Ingredient;
import com.zust.buy.good.mapper.IngredientMapper;
import com.zust.buy.good.service.IIngredientService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 食材表 服务实现类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-14
 */
@Service
public class IngredientServiceImpl extends ServiceImpl<IngredientMapper, Ingredient> implements IIngredientService {

}
