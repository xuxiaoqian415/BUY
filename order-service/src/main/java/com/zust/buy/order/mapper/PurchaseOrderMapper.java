package com.zust.buy.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.buy.common.entity.PurchaseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 采购表 Mapper 接口
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-15
 */
@Mapper
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrder> {

    List<PurchaseOrder> getPurchaseData(@Param("orderId") Integer orderId, @Param("dressing") Boolean dressing);
}
