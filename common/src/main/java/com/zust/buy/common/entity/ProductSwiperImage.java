package com.zust.buy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜品详情轮播图表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_product_swiper_image")
public class ProductSwiperImage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 图片名
     */
    private String image;

    /**
     * 轮播排序
     */
    private Integer sort;

    /**
     * 所属商品id
     */
    @TableField("productId")
    private Integer productId;


}
