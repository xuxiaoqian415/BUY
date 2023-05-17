package com.zust.buy.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.buy.common.entity.Order;
import com.zust.buy.common.entity.PurchaseOrder;
import com.zust.buy.order.mapper.PurchaseOrderMapper;
import com.zust.buy.order.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 采购表 服务实现类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-15
 */
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements IPurchaseOrderService {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public void createPurchaseOrder(Order order) {
        List<PurchaseOrder> purchaseList = purchaseOrderMapper.getPurchaseData(order.getId(), order.getDressing());
        for (PurchaseOrder item : purchaseList) {
            this.save(item);
            item.setId(null);
        }
    }
}
