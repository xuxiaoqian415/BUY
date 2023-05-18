package com.zust.buy.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜品食材关系表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_product_ingredient")
public class ProductIngredient extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer productId;

    private Integer ingredientId;

    @TableField(exist = false)
    private String ingredientName;

    /**
     * 一份菜品对应的食材数量
     */
    private Integer number;

    /**
     * 原料单位
     */
    @TableField(exist = false)
    private String unit;

    /**
     * 原料描述
     */
    @TableField(exist = false)
    private String description;

}
