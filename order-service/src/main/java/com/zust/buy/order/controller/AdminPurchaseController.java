package com.zust.buy.order.controller;

import com.zust.buy.common.entity.PageBean;
import com.zust.buy.common.entity.PurchaseOrder;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.order.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/purchase")
public class AdminPurchaseController {

    @Autowired
    private IPurchaseOrderService purchaseOrderService;

    /**
     * 按条件分页查询采购信息
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public ResponseData getPurchaseList(@RequestBody PageBean pageBean) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderNo", pageBean.getQuery());
        params.put("deliveryDate", pageBean.getDateValue());
        params.put("start", pageBean.getStart());
        params.put("pageSize", pageBean.getPageSize());
        Map<String, Object> result = purchaseOrderService.getPurchaseList(params);
        return ResponseData.ok(result);
    }

    @RequestMapping("/getListByDate")
    public ResponseData getListByDate(@RequestParam("date") String date) {
        List<PurchaseOrder> list = purchaseOrderService.getListByDate(date);
        return ResponseData.ok(list);
    }
}
