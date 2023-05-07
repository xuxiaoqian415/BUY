package com.zust.buy.order.controller;

import com.zust.buy.common.entity.PageBean;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.entity.Order;
import com.zust.buy.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 根据条件分页查询订单
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public ResponseData getOrderList(@RequestBody PageBean pageBean) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderNo", pageBean.getQuery());
        params.put("start", pageBean.getStart());
        params.put("pageSize", pageBean.getPageSize());
        List<Order> orderList = orderService.getOrderList(params);
        System.out.println(orderList);
        Map<String, Object> result = new HashMap<>();
        result.put("orderList", orderList);
        result.put("total", 1);
        return ResponseData.ok(result);
    }
}

