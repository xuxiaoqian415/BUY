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
 * 食材表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_ingredient")
public class Ingredient extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 食材名称
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否是调味料 0-不是 1-是
     */
    private Boolean isDressing;


}
