package com.zust.buy.good.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.buy.common.entity.*;
import com.zust.buy.common.util.DateUtil;
import com.zust.buy.common.util.StringUtil;
import com.zust.buy.good.service.IBigTypeService;
import com.zust.buy.good.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/bigType")
public class AdminBigTypeController {

    @Autowired
    private IBigTypeService bigTypeService;
    @Autowired
    private ISmallTypeService smallTypeService;

    @Value("${imagesFilePath.bigType}")
    private String bigTypeImagesPath;

    /**
     * 根据条件获取大类列表
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public ResponseData getBigTypeList(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<BigType> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        IPage<BigType> pageResult = bigTypeService.page(page, new QueryWrapper<BigType>()
                .like(!StringUtil.isEmpty(query), "name", query).eq("deleted", 0));
        Map<String, Object> result = new HashMap<>();
        result.put("bigTypeList", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        return ResponseData.ok(result);
    }

    /**
     * 新增或修改大类
     * @param bigType
     * @return
     */
    @PostMapping("/save")
    public ResponseData saveBigType(@RequestBody BigType bigType) {
        if (null == bigType.getId() || -1 == bigType.getId()) {
            bigTypeService.save(bigType);
        } else {
            bigType.setCreateTime(null);
            bigType.setUpdateTime(null);
            bigTypeService.updateById(bigType);
        }
        return ResponseData.ok();
    }

    @RequestMapping("/detail/{id}")
    public ResponseData getBigDetail(@PathVariable("id") Integer id) {
        BigType bigType = bigTypeService.getById(id);
        return ResponseData.ok(bigType);
    }

    /**
     * 删除商品大类
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public ResponseData delete(@PathVariable("id") Integer id) {
        int count = smallTypeService.count(new QueryWrapper<SmallType>()
                .eq("big_type_id", id).eq("deleted", 0));
        if (count > 0) {
            return ResponseData.error("该大类下有小类信息，不能删除！");
        }
        BigType bigType = new BigType();
        bigType.setId(id);
        bigType.setDeleted(1);
        bigTypeService.updateById(bigType);
        return ResponseData.ok();
    }

    /**
     * 上传商品大类图片
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadImage")
    public ResponseData uploadImage(MultipartFile file) {
        Map<String, Object> dataMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件名的后缀名
            String suffixName = file.getOriginalFilename().substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            File newFile = new File(bigTypeImagesPath + newFileName);
            try {
                file.transferTo(newFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            dataMap.put("imageName", newFileName);
            dataMap.put("src", "image/image/bigType/" + newFileName);
        }
        return ResponseData.ok(dataMap);
    }

    /**
     * 获取大类下拉框数据
     * @return
     */
    @RequestMapping("/getSelectList")
    public ResponseData getSelectList() {
        List<BigType> list = bigTypeService.list(new QueryWrapper<BigType>().eq("deleted", 0));
        return ResponseData.ok(list);
    }

}
