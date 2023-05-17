package com.zust.buy.order.mapper;

import com.zust.buy.common.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-01
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> getOrderList(Map<String, Object> params);

    Integer getTotal(Map<String, Object> params);

}
