package com.zust.buy.user.service;

import com.zust.buy.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("wx-service")
public interface WxUserInfoService {

    @RequestMapping("/wx/user/list")
    Map<String, Object> getUserList(@RequestBody PageBean pageBean);

    @RequestMapping("/wx/user/updateStatus/{id}/{status}")
    void deleteWxUser(@PathVariable("id") Integer id, @PathVariable("status") Integer status);

}
