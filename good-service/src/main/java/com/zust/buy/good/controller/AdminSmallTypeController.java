package com.zust.buy.good.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zust.buy.common.entity.*;
import com.zust.buy.good.service.IBigTypeService;
import com.zust.buy.good.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/smallType")
public class AdminSmallTypeController {

    @Autowired
    private ISmallTypeService smallTypeService;
    @Autowired
    private IBigTypeService bigTypeService;

    /**
     * 根据条件分页查询商品小类
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public ResponseData getSmallTypeList(@RequestBody PageBean pageBean) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", pageBean.getQuery());
        params.put("bigTypeId", pageBean.getBigTypeId());
        params.put("start", pageBean.getStart());
        params.put("pageSize", pageBean.getPageSize());
        Map<String, Object> result = smallTypeService.getSmallTypeList(params);
        return ResponseData.ok(result);
    }

    @RequestMapping("/delete/{id}")
    public ResponseData delete(@PathVariable("id") Integer id) {
        SmallType entity = new SmallType();
        entity.setId(id);
        entity.setDeleted(1);
        smallTypeService.updateById(entity);
        return ResponseData.ok();
    }

    /**
     * 新增或修改小类
     * @param smallType
     * @return
     */
    @PostMapping("/save")
    public ResponseData saveSmallType(@RequestBody SmallType smallType) {
        if (null == smallType.getId() || -1 == smallType.getId()) {
            smallTypeService.save(smallType);
        } else {
            smallType.setCreateTime(null);
            smallType.setUpdateTime(null);
            smallTypeService.updateById(smallType);
        }
        return ResponseData.ok();
    }

    @RequestMapping("/detail/{id}")
    public ResponseData getSmallDetail(@PathVariable("id") Integer id) {
        SmallType smallType = smallTypeService.getById(id);
        smallType.setBigType(bigTypeService.getById(smallType.getBigTypeId()));
        return ResponseData.ok(smallType);
    }

    /**
     * 获取小类下拉框数据
     * @return
     */
    @RequestMapping("/getSelectList")
    public ResponseData getSelectList(@RequestParam("bigTypeId") Integer bigTypeId) {
        List<SmallType> list;
        if (bigTypeId == 0) {
            list = smallTypeService.list(new QueryWrapper<SmallType>());
        } else {
            list = smallTypeService.list(new QueryWrapper<SmallType>().eq("big_type_id", bigTypeId));
        }
        return ResponseData.ok(list);
    }
}
