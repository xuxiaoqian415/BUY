package com.zust.buy.order.service.impl;

import com.zust.buy.common.entity.WxUserInfo;
import com.zust.buy.common.entity.Order;
import com.zust.buy.order.mapper.OrderMapper;
import com.zust.buy.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-01
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getOrderList(Map<String, Object> params) {
        List<Order> orderList = orderMapper.getOrderList(params);
        for (Order order : orderList) {
            WxUserInfo wxUserInfo = order.getWxUserInfo();
            order.setNickName(wxUserInfo.getNickName());
        }
        return orderList;
    }
}
