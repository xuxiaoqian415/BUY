package com.zust.buy.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.buy.common.entity.OrderDetail;
import com.zust.buy.order.mapper.OrderDetailMapper;
import com.zust.buy.order.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 详细订单快照表 服务实现类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-21
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
