package com.zust.buy.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zust.buy.common.entity.*;
import com.zust.buy.order.service.IOrderDetailService;
import com.zust.buy.order.service.IOrderService;
import com.zust.buy.order.service.IPurchaseOrderService;
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
    @Autowired
    private IPurchaseOrderService purchaseOrderService;

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
        Order order = new Order();
        order.setId(data.getId());
        order.setStatus(data.getStatus());
        orderService.updateById(order);
        return ResponseData.ok();
    }

    @RequestMapping("/delete/{id}")
    public ResponseData delete(@PathVariable("id") Integer id) {
        String status = orderService.getById(id).getStatus();
        // 判断类型
        if ("2".equals(status) || "3".equals(status) || "5".equals(status)) {
            return ResponseData.error("该状态下订单不能删除！");
        }
        Order entity = new Order();
        entity.setId(id);
        entity.setDeleted(1);
        orderService.updateById(entity);
        // 关联删除
        orderDetailService.deleteByMainId(id);
        purchaseOrderService.remove(new QueryWrapper<PurchaseOrder>().eq("order_id", id));
        return ResponseData.ok();
    }

    @RequestMapping("/detail/list/{id}")
    public ResponseData getDetailList(@PathVariable("id") Integer id) {
        List<OrderDetail> detailList = orderDetailService.list(new QueryWrapper<OrderDetail>()
                .eq("main_id", id).eq("deleted", 0));
        return ResponseData.ok(detailList);
    }

}

