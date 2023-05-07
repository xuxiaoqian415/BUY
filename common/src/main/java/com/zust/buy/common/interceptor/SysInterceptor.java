package com.zust.buy.common.interceptor;

import com.zust.buy.common.constant.SystemConstant;
import com.zust.buy.common.util.JwtUtils;
import com.zust.buy.common.util.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统拦截器
 */
public class SysInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (handler instanceof HandlerMethod) {
            String token = request.getHeader("token");
            if (StringUtil.isEmpty(token)) {
                response.setStatus(SystemConstant.JWT_ERRCODE_NULL);
                return false;
//                throw new RuntimeException("签名不存在！");
            }
            Claims claims = JwtUtils.validateJWT(token).getClaims();
            if (null == claims) {
                response.setStatus(SystemConstant.JWT_ERRCODE_EXPIRE);
                return false;
//                throw new RuntimeException("鉴权失败！");
            }
            if (uri.startsWith("/user/admin")) {
                if (!claims.getSubject().equals("admin") || !claims.getId().equals("-1")) {
                    throw new RuntimeException("管理员鉴权失败！");
                }
                System.out.println("鉴权成功！");
            }
        }
        return true;
    }
}
