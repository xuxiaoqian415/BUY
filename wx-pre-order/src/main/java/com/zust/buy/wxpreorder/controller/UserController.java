package com.zust.buy.wxpreorder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.buy.common.constant.SystemConstant;
import com.zust.buy.common.entity.PageBean;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.util.HttpClientUtil;
import com.zust.buy.common.util.JwtUtils;
import com.zust.buy.common.util.StringUtil;
import com.zust.buy.common.entity.WxUserInfo;
import com.zust.buy.wxpreorder.property.WeixinProperty;
import com.zust.buy.wxpreorder.service.IWxUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  微信用户操作相关 控制器
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/wx/user")
public class UserController {

    @Autowired
    private WeixinProperty weixinProperty;
    @Autowired
    private HttpClientUtil httpClientUtil;
    @Autowired
    private IWxUserInfoService wxUserInfoService;

    @PostMapping("/login")
    public ResponseData login(@RequestBody WxUserInfo wxUserInfo) {
        StringBuilder jscode2sessionUrl = new StringBuilder(weixinProperty.getJscode2sessionUrl());
        jscode2sessionUrl.append("?appid=").append(weixinProperty.getAppId())
            .append("&secret=").append(weixinProperty.getSecret())
            .append("&js_code=").append(wxUserInfo.getCode())
            .append("&grant_type=authorization_code");
        String result = httpClientUtil.sendHttpGet(jscode2sessionUrl.toString());
        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.get("openid").toString();
        // 判断用户是否存在
        WxUserInfo userInfo = wxUserInfoService.getOne(new QueryWrapper<WxUserInfo>().eq("open_id", openid));
        if (null == userInfo) {
            wxUserInfo.setOpenId(openid);
            wxUserInfo.setRegisterDate(new Date());
            wxUserInfo.setLastLoginDate(new Date());
            wxUserInfoService.save(wxUserInfo);
        } else {
            userInfo.setNickName(wxUserInfo.getNickName());
            userInfo.setAvatarUrl(wxUserInfo.getAvatarUrl());
            userInfo.setLastLoginDate(new Date());
            userInfo.setCreateTime(null);
            userInfo.setUpdateTime(null);
            wxUserInfoService.updateById(userInfo);
        }
        // 用JWT生成token返回前端
        String token = JwtUtils.createJWT(openid, wxUserInfo.getNickName(), SystemConstant.JWT_TTL);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        return ResponseData.ok(resultMap);
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> getUserList(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<WxUserInfo> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        IPage<WxUserInfo> pageResult = wxUserInfoService.page(page, new QueryWrapper<WxUserInfo>().like(!StringUtil.isEmpty(query), "nick_name", query));
        Map<String, Object> result = new HashMap<>();
        result.put("userList", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateStatus/{id}/{status}")
    public void deleteWxUser(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
        WxUserInfo user = new WxUserInfo();
        user.setId(id);
        user.setDeleted(status);
        wxUserInfoService.updateById(user);
    }
}
