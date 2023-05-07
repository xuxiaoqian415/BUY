package com.zust.buy.wxpreorder.mapper;

import com.zust.buy.common.entity.WxUserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 微信用户信息表 Mapper 接口
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-19
 */
@Mapper
public interface WxUserInfoMapper extends BaseMapper<WxUserInfo> {

    WxUserInfo getByOpenId(String openId);

}
