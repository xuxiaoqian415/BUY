package com.zust.buy.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zust.buy.common.entity.OrderDetail;
import com.zust.buy.common.entity.PageBean;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.entity.Order;
import com.zust.buy.order.service.IOrderDetailService;
import com.zust.buy.order.service.IOrderService;
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
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderDetailService orderDetailService;

    /**
     * 根据条件分页查询订单
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public ResponseData getOrderList(@RequestBody PageBean pageBean) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderNo", pageBean.getQuery());
        params.put("status", pageBean.getStatus());
        params.put("dateValue", pageBean.getDateValue());
        params.put("start", pageBean.getStart());
        params.put("pageSize", pageBean.getPageSize());
        Map<String, Object> result = orderService.getOrderList(params);
        return ResponseData.ok(result);
    }

    @PostMapping("/update/status")
    public ResponseData updateStatus(@RequestBody Order data) {
        Order order = orderService.getById(data.getId());
        order.setStatus(data.getStatus());
        order.setCreateTime(null);
        order.setUpdateTime(null);
        orderService.updateById(order);
        return ResponseData.ok();
    }

    @RequestMapping("/delete/{id}")
    public ResponseData delete(@PathVariable("id") Integer id) {
        Order entity = new Order();
        entity.setId(id);
        entity.setDeleted(1);
        orderService.updateById(entity);
        return ResponseData.ok();
    }

    @RequestMapping("/detail/list/{id}")
    public ResponseData getDetailList(@PathVariable("id") Integer id) {
        List<OrderDetail> detailList = orderDetailService.list(new QueryWrapper<OrderDetail>().eq("main_id", id));
        return ResponseData.ok(detailList);
    }

}

