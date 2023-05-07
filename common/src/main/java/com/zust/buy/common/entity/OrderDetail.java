package com.zust.buy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 详细订单快照表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_order_detail")
public class OrderDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 主订单id
     */
    private Integer mainId;

    /**
     * 商品id
     */
    private Integer goodId;

    /**
     * 商品数量
     */
    private Integer goodNum;

    /**
     * 商品单价
     */
    private BigDecimal goodPrice;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品图片
     */
    private String goodPic;


}
