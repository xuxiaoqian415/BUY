package com.zust.buy.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.util.DateUtil;
import com.zust.buy.common.util.JwtUtils;
import com.zust.buy.common.util.StringUtil;
import com.zust.buy.common.entity.Order;
import com.zust.buy.common.entity.OrderDetail;
import com.zust.buy.order.service.IOrderDetailService;
import com.zust.buy.order.service.IOrderService;
import com.zust.buy.order.service.IPurchaseOrderService;
import io.jsonwebtoken.Claims;
import javafx.beans.property.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private IPurchaseOrderService purchaseOrderService;

    @ResponseBody
    @RequestMapping("/create")
    @Transactional
    public String create(@RequestBody Order order, @RequestHeader(value = "token") String token) {
        Claims claims = JwtUtils.validateJWT(token).getClaims();
        if (null == claims) {
            return "";
        }
        String openId = claims.getId();
        order.setUserId(openId);
        String orderNo = orderService.createUserOrder(order);
        purchaseOrderService.createPurchaseOrder(order);
        return orderNo;
    }

    @ResponseBody
    @RequestMapping("/preparePay")
    public Map<String, Object> preparePay(@RequestBody String orderNo) {
        System.out.println("模拟预支付，订单号："+orderNo);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("prePayId", "wx0123456789");
        resultMap.put("appId", "wx3d199279cb3da01f");
        resultMap.put("timeStamp", System.currentTimeMillis()/1000+"");
        resultMap.put("nonceStr", StringUtil.getRandomString(32));
        resultMap.put("package","prepay_id="+resultMap.get("prepay_id"));
        resultMap.put("signType","MD5");
//        resultMap.put("paySign",getSign(resultMap));
        resultMap.put("orderNo",orderNo);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/update")
    public void updateStatus(@RequestBody String orderNo) {
        Order order = orderService.getOne(new QueryWrapper<Order>().eq("order_no", orderNo));
        if (null != order && order.getStatus().equals("1")) {
            order.setPayDate(new Date());
            order.setStatus("2");
            orderService.saveOrUpdate(order);
        }
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(Integer type, Integer page, Integer pageSize) {
        IPage<Order> orderPage = new Page<>(page, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<Order>().orderByDesc("id").eq("deleted", 0);
        if (type == 0) {
            orderPage = orderService.page(orderPage, queryWrapper);
        } else if (type == 3) {
            queryWrapper.eq("status", 3).or().eq("status", 4)
                    .or().eq("status", 5).or().eq("status", 6);
            orderPage = orderService.page(orderPage, queryWrapper);
        } else {
            orderPage = orderService.page(orderPage, queryWrapper.eq("status", type));
        }
        Map<String, Object> result = new HashMap<>();
        result.put("list", orderPage.getRecords());
        result.put("total", orderPage.getTotal());
        result.put("totalPage", orderPage.getPages());
        return result;
    }

    @ResponseBody
    @RequestMapping("/getOne/{id}")
    public Order getOrderById(@PathVariable("id") Integer id) {
        return orderService.getById(id);
    }

    @ResponseBody
    @RequestMapping("/detail/list/{id}")
    public List<OrderDetail> getDetailList(@PathVariable("id") Integer id) {
        return orderDetailService.list(new QueryWrapper<OrderDetail>().eq("main_id", id));
    }

    @ResponseBody
    @RequestMapping ("/update/{id}/status/{status}")
    public void updateStatus(@PathVariable("id") Integer id, @PathVariable("status") String status) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        orderService.updateById(order);
    }

}

