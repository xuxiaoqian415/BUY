package com.zust.buy.order.service;

import com.zust.buy.common.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-01
 */
public interface IOrderService extends IService<Order> {

    Map<String, Object> getOrderList(Map<String, Object> params);

    String createUserOrder(Order order);


    void updateToPurchased(String deliveryDate);

}
