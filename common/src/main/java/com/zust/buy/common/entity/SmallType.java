package com.zust.buy.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 小类表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_small_type")
public class SmallType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String remark;

    @TableField("bigTypeId")
    private Integer bigTypeId;

    /**
     * 所属大类
     */
    @TableField(select = false)
    private BigType bigType;

    /**
     * 商品集合
     */
    @TableField(select = false)
    private List<Product> productList;


}
