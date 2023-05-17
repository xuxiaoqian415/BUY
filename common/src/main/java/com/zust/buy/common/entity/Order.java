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

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_order")
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id(微信openId)
     */
    private String userId;

    @TableField(select = false, exist = false)
    private WxUserInfo wxUserInfo;

    @TableField(select = false, exist = false)
    private String nickName;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 联系电话
     */
    private String telNumber;

    /**
     * 支付日期
     */
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payDate;

    /**
     * 预订配送日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    /**
     * 订单状态 1-未支付 2-已支付/待发货 3-已发货
     */
    private String status;

    /**
     * 订单商品详情
     */
    @TableField(select = false, exist = false)
    private OrderDetail[] goods;

    /**
     * 是否需要调料
     */
    @TableField(select = false, exist = false)
    private Boolean dressing;

}
