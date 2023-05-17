package com.zust.buy.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zust.buy.common.entity.Order;
import com.zust.buy.common.entity.PurchaseOrder;

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

}
