package com.zust.buy.order.service.impl;

import com.zust.buy.common.entity.OrderDetail;
import com.zust.buy.common.entity.WxUserInfo;
import com.zust.buy.common.entity.Order;
import com.zust.buy.common.util.DateUtil;
import com.zust.buy.order.mapper.OrderMapper;
import com.zust.buy.order.service.IOrderDetailService;
import com.zust.buy.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    @Autowired
    private IOrderDetailService orderDetailService;

    @Override
    public Map<String, Object> getOrderList(Map<String, Object> params) {
        List<Order> orderList = orderMapper.getOrderList(params);
        Integer total = orderMapper.getTotal(params);
        Map<String, Object> result = new HashMap<>();
        result.put("orderList", orderList);
        result.put("total", total);
        return result;
    }

    @Override
    public String createUserOrder(Order order) {
        order.setOrderNo(DateUtil.getCurrentDateStr());
        this.save(order);
        OrderDetail[] goods = order.getGoods();
        for (OrderDetail good: goods) {
            good.setMainId(order.getId());
            orderDetailService.save(good);
        }
        return order.getOrderNo();
    }

}
