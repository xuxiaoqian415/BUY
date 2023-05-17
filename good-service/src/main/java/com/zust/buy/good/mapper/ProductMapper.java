package com.zust.buy.good.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.buy.common.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-14
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> getProductList(Map<String, Object> params);

    Integer getTotal(Map<String, Object> params);

}
