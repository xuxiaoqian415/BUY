package com.zust.buy.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zust.buy.common.entity.OrderDetail;

/**
 * <p>
 * 详细订单快照表 服务类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-21
 */
public interface IOrderDetailService extends IService<OrderDetail> {

    void deleteByMainId(Integer orderId);

}
