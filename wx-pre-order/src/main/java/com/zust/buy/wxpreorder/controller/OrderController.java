package com.zust.buy.wxpreorder.controller;

import com.zust.buy.common.entity.Order;
import com.zust.buy.common.entity.OrderDetail;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.wxpreorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/wx/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public ResponseData create(@RequestBody Order order, @RequestHeader(value = "token") String token) {
        String orderNo = orderService.create(order, token);
        if (orderNo.equals("")) {
            return ResponseData.error("token过期");
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("orderNo", orderNo);
        return ResponseData.ok(resultMap);
    }

    @RequestMapping("/preparePay")
    public ResponseData preparePay(@RequestBody String orderNo) {
        Map<String, Object> resultMap = orderService.preparePay(orderNo);
        return ResponseData.ok(resultMap);
    }

    @RequestMapping("/update")
    public ResponseData updateStatus(@RequestBody String orderNo) {
        orderService.updateStatus(orderNo);
        return ResponseData.ok();
    }

    @RequestMapping("/list")
    public ResponseData list(Integer type, Integer page, Integer pageSize) {
        Map<String, Object> result = orderService.list(type, page, pageSize);
        return ResponseData.ok(result);
    }

    @RequestMapping("/getOne/{id}")
    public ResponseData getOrderById(@PathVariable("id") Integer id) {
        Order order = orderService.getOrderById(id);
        return ResponseData.ok(order);
    }

    @RequestMapping("/detail/list/{id}")
    public ResponseData getDetailList(@PathVariable("id") Integer id) {
        List<OrderDetail> list = orderService.getDetailList(id);
        return ResponseData.ok(list);
    }

    @RequestMapping ("/update/{id}/status/{status}")
    public ResponseData updateStatus(@PathVariable("id") Integer id, @PathVariable("status") String status) {
        orderService.updateStatus(id, status);
        return ResponseData.ok();
    }
}

