package com.zust.buy.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zust.buy.common.config.CustomDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 微信用户信息表
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_wx_user_info")
public class WxUserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户唯一标识
     */
    private String openId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 注册日期
     */
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date registerDate;

    /**
     * 最后登录日期
     */
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date lastLoginDate;

    @TableField(select = false, exist = false)
    private String code;


}
