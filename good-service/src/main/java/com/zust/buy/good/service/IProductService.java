package com.zust.buy.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zust.buy.common.entity.Product;

import java.util.Map;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-14
 */
public interface IProductService extends IService<Product> {

    Map<String, Object> getProductList(Map<String, Object> params);

}
