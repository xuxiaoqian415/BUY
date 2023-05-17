package com.zust.buy.good.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zust.buy.common.entity.SmallType;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-15
 */
public interface ISmallTypeService extends IService<SmallType> {

    Map<String, Object> getSmallTypeList(Map<String, Object> params);
}
