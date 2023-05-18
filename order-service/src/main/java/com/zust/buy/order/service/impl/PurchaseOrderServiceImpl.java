package com.zust.buy.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.buy.common.entity.Order;
import com.zust.buy.common.entity.PurchaseOrder;
import com.zust.buy.order.mapper.PurchaseOrderMapper;
import com.zust.buy.order.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getPurchaseList(Map<String, Object> params) {
        List<PurchaseOrder> list = purchaseOrderMapper.getPurchaseList(params);
        Integer total = purchaseOrderMapper.getTotal(params);
        Map<String, Object> result = new HashMap<>();
        result.put("purchaseList", list);
        result.put("total", total);
        return result;
    }

    @Override
    public List<PurchaseOrder> getListByDate(String date) {
        return purchaseOrderMapper.getListByDate(date);
    }
}
