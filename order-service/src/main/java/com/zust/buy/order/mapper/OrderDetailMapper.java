package com.zust.buy.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.buy.common.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 详细订单快照表 Mapper 接口
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-21
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    void deleteByMainId(@Param("orderId") Integer orderId);

}
