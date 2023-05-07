package com.zust.buy.user.service.impl;

import com.zust.buy.common.entity.User;
import com.zust.buy.user.mapper.UserMapper;
import com.zust.buy.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuxiaoqian
 * @since 2023-04-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
