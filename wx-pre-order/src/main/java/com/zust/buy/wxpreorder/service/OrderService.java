package com.zust.buy.wxpreorder.service;

import com.zust.buy.common.entity.Order;
import com.zust.buy.common.entity.OrderDetail;
import com.zust.buy.common.entity.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("order-service")
public interface OrderService {

    @RequestMapping("/order/create")
    String create(@RequestBody Order order, @RequestHeader(value = "token") String token);

    @RequestMapping("/order/preparePay")
    Map<String, Object> preparePay(@RequestBody String orderNo);

    @RequestMapping("/order/update")
    void updateStatus(@RequestBody String orderNo);

    @RequestMapping("/order/list")
    Map<String, Object> list(@RequestParam Integer type, @RequestParam Integer page, @RequestParam Integer pageSize);

    @RequestMapping("/order/getOne/{id}")
    Order getOrderById(@PathVariable("id") Integer id);

    @RequestMapping("/order/detail/list/{id}")
    List<OrderDetail> getDetailList(@PathVariable("id") Integer id);

    @RequestMapping ("/order/update/{id}/status/{status}")
    void updateStatus(@PathVariable("id") Integer id, @PathVariable("status") String status);

}
