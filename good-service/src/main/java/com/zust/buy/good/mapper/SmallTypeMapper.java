package com.zust.buy.good.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.buy.common.entity.SmallType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  小类表 Mapper 接口
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-15
 */
@Mapper
public interface SmallTypeMapper extends BaseMapper<SmallType> {

    List<SmallType> getSmallTypeList(Map<String, Object> params);

    Integer getTotal(Map<String, Object> params);

}
