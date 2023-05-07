package com.zust.buy.wxpreorder.service;

import com.zust.buy.common.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
