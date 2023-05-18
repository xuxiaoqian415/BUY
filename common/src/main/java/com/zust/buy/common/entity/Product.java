package com.zust.buy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zust.buy.common.config.CustomDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 产品图片
     */
    @TableField("proPic")
    private String proPic;

    /**
     * 菜品描述
     */
    private String description;

    /**
     * 是否热卖 0-否 1-是
     */
    @TableField("isHot")
    private Boolean isHot;

    /**
     * 是否轮播 0-否 1-是
     */
    @TableField("isSwiper")
    private Boolean isSwiper;

    /**
     * 首页轮播图地址
     */
    @TableField("swiperPic")
    private String swiperPic;

    /**
     * 轮播排序
     */
    @TableField("swiperSort")
    private Integer swiperSort;

    /**
     * 类别ID
     */
    @TableField("typeId")
    private Integer typeId;

    /**
     * 所属小类名称
     */
    @TableField(exist = false)
    private String typeName;

    /**
     * 热卖时间
     */
    @TableField("hotDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hotDateTime;

    /**
     * 配料文本描述
     */
    private String ingredient;

    /**
     * 步骤
     */
    private String steps;

    /**
     * 详情页轮播图集合
     */
    @TableField(select = false, exist = false)
    private List<ProductSwiperImage> swiperImageList;

    @TableField(select = false, exist = false)
    private Integer bigTypeId;

}
