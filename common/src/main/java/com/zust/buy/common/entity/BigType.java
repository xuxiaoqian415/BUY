package com.zust.buy.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 大类表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_big_type")
public class BigType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String remark;

    /**
     * 首图
     */
    private String image = "default.jpg";
}
