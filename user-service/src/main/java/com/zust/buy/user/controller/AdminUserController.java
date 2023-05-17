package com.zust.buy.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.buy.common.constant.SystemConstant;
import com.zust.buy.common.entity.PageBean;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.util.JwtUtils;
import com.zust.buy.common.util.StringUtil;
import com.zust.buy.common.entity.User;
import com.zust.buy.user.service.IUserService;
import com.zust.buy.user.service.WxUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-28
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private WxUserInfoService wxUserInfoService;


    /**
     * 管理员登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public ResponseData adminLogin(@RequestBody User user) {
        if (null == user) {
            return ResponseData.error();
        }
        if (StringUtil.isEmpty(user.getUserName())) {
            return ResponseData.error("用户名不能为空！");
        }
        if (StringUtil.isEmpty(user.getPassword())) {
            return ResponseData.error("密码不能为空！");
        }
        User one = userService.getOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (null == one) {
            return ResponseData.error("用户名不存在！");
        }
        if (!one.getPassword().trim().equals(user.getPassword())) {
            return ResponseData.error("用户名或密码错误！");
        }
        String token = JwtUtils.createJWT("-1", "admin", SystemConstant.JWT_TTL);
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        return ResponseData.ok(result);
    }

    /**
     * 根据条件分页查询后台用户信息
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public ResponseData getAdminList(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<User> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        IPage<User> pageResult = userService.page(page, new QueryWrapper<User>().like(!StringUtil.isEmpty(query), "user_name", query));
        Map<String, Object> result = new HashMap<>();
        result.put("userList", pageResult.getRecords());
        result.put("total", pageResult.getTotal());
        return ResponseData.ok(result);
    }

    /**
     * 根据条件分页查询微信用户信息
     * @param pageBean
     * @return
     */
    @RequestMapping("/wx/list")
    public ResponseData getUserList(@RequestBody PageBean pageBean) {
        Map<String, Object> result = wxUserInfoService.getUserList(pageBean);
        return ResponseData.ok(result);
    }

}

