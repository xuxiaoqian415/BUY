package com.zust.buy.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zust.buy.common.entity.ResponseData;
import com.zust.buy.common.entity.User;
import com.zust.buy.common.util.StringUtil;
import com.zust.buy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 修改密码
     * @param user
     * @return
     */
    @RequestMapping("/modifyPassword")
    public ResponseData modifyPassword(@RequestBody User user) {
        if (StringUtil.isEmpty(user.getUserName())) {
            return ResponseData.error("用户名不能为空");
        }
        if (StringUtil.isEmpty(user.getNewPassword())) {
            return ResponseData.error("新密码不能为空");
        }
        User one = userService.getOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (null == one) {
            return ResponseData.error("用户不存在！");
        }
        if (!one.getPassword().trim().equals(user.getPassword())) {
            return ResponseData.error("原密码错误！");
        }
        user.setId(one.getId());
        user.setPassword(user.getNewPassword());
        userService.saveOrUpdate(user);
        return ResponseData.ok();
    }
}
