package com.zust.buy.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zust.buy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采购表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_purchase_order")
public class PurchaseOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 食材ID
     */
    private Integer ingredientId;

    /**
     * 对应订单ID
     */
    private Integer orderId;

    /**
     * 总数
     */
    private Integer totalNum;

    /**
     * 食材名称
     */
    @TableField(exist = false)
    private String name;

    /**
     * 食材单位
     */
    @TableField(exist = false)
    private String unit;

    /**
     * 食材描述
     */
    @TableField(exist = false)
    private String description;


}
