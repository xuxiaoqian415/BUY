package com.zust.buy.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zust.buy.common.entity.Order;
import com.zust.buy.common.entity.PurchaseOrder;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采购表 服务类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-15
 */
public interface IPurchaseOrderService extends IService<PurchaseOrder> {

    void createPurchaseOrder(Order order);

    Map<String, Object> getPurchaseList(Map<String, Object> params);

    List<PurchaseOrder> getListByDate(String date);

}
