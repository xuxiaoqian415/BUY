package com.zust.buy.good.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.buy.common.entity.Order;
import com.zust.buy.common.entity.SmallType;
import com.zust.buy.good.mapper.SmallTypeMapper;
import com.zust.buy.good.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-15
 */
@Service
public class SmallTypeServiceImpl extends ServiceImpl<SmallTypeMapper, SmallType> implements ISmallTypeService {

    @Autowired
    private SmallTypeMapper smallTypeMapper;

    @Override
    public Map<String, Object> getSmallTypeList(Map<String, Object> params) {
        List<SmallType> smallTypeList = smallTypeMapper.getSmallTypeList(params);
        Integer total = smallTypeMapper.getTotal(params);
        Map<String, Object> result = new HashMap<>();
        result.put("smallTypeList", smallTypeList);
        result.put("total", total);
        return result;
    }
}
