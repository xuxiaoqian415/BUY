package com.zust.buy.good.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.buy.common.entity.Product;
import com.zust.buy.good.mapper.ProductMapper;
import com.zust.buy.good.service.IProductService;
import org.springframework.stereotype.Service;

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

}
