package com.zust.buy.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zust.buy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @TableField(exist = false)
    private String orderNo;

    /**
     * 总数
     */
    private Integer totalNum;

    /**
     * 食材名称
     */
    @TableField(exist = false)
    private String ingredientName;

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

    /**
     * 预订配送日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @TableField(exist = false)
    private Date deliveryDate;

}
