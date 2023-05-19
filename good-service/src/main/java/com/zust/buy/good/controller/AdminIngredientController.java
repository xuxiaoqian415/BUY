package com.zust.buy.good.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.buy.common.entity.*;
import com.zust.buy.common.util.StringUtil;
import com.zust.buy.good.service.IIngredientService;
import com.zust.buy.good.service.IProductIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/ingredient")
public class AdminIngredientController {

    @Autowired
    private IIngredientService ingredientService;
    @Autowired
    private IProductIngredientService productIngredientService;

    /**
     * 根据条件分页查询商品
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public ResponseData getIngredientList(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<Ingredient> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        IPage<Ingredient> pageResult = ingredientService.page(page, new QueryWrapper<Ingredient>()
                .like(!StringUtil.isEmpty(query), "name", query)
                .eq("deleted", 0));
        Map<String, Object> result = new HashMap<>();
        result.put("ingredientList", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        return ResponseData.ok(result);
    }

    /**
     * 删除原料
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public ResponseData delete(@PathVariable("id") Integer id) {
        int count = productIngredientService.count(new QueryWrapper<ProductIngredient>()
                .eq("ingredient_id", id));
        if (count > 0) {
            return ResponseData.error("该原料有关联的菜品，不能删除！");
        }
        Ingredient entity = new Ingredient();
        entity.setId(id);
        entity.setDeleted(1);
        ingredientService.updateById(entity);
        return ResponseData.ok();
    }

    @RequestMapping("/detail/{id}")
    public ResponseData getDetail(@PathVariable("id") Integer id) {
        Ingredient ingredient = ingredientService.getById(id);
        return ResponseData.ok(ingredient);
    }

    /**
     * 新增或修改原料
     * @param entity
     * @return
     */
    @PostMapping("/save")
    public ResponseData saveIngredient(@RequestBody Ingredient entity) {
        if (null == entity.getId() || -1 == entity.getId()) {
            ingredientService.save(entity);
        } else {
            entity.setCreateTime(null);
            entity.setUpdateTime(null);
            ingredientService.updateById(entity);
        }
        return ResponseData.ok();
    }

    /**
     * 根据菜品id获取该菜品下的所有原料列表
     * @param productId
     * @return
     */
    @RequestMapping("/listByProduct/{id}")
    public ResponseData listByProduct(@PathVariable("id") Integer productId) {
        List<ProductIngredient> list = productIngredientService.listByProductId(productId);
        return ResponseData.ok(list);
    }

    /**
     * 删除菜品关联的原料
     * @param id
     * @return
     */
    @RequestMapping("/deleteFromProduct/{id}")
    public ResponseData deleteFromProduct(@PathVariable("id") Integer id) {
        productIngredientService.removeById(id);
        return ResponseData.ok();
    }

    /**
     * 获取原料下拉框数据
     * @return
     */
    @RequestMapping("/getSelectList")
    public ResponseData getSelectList() {
        List<Ingredient> list = ingredientService.list(new QueryWrapper<Ingredient>().eq("deleted", 0));
        return ResponseData.ok(list);
    }

    /**
     * 新增菜品关联的原料
     * @param entity
     * @return
     */
    @PostMapping("/addToProduct")
    public ResponseData addToProduct(@RequestBody ProductIngredient entity) {
        int count = productIngredientService.count(new QueryWrapper<ProductIngredient>()
                .eq("ingredient_id", entity.getIngredientId())
                .eq("product_id", entity.getProductId()));
        if (count == 0) {
            productIngredientService.save(entity);
        } else {
            productIngredientService.addNumber(entity);
        }
        return ResponseData.ok();
    }

}
