package com.zust.buy.common.config;

import com.zust.buy.common.interceptor.SysInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 项目配置类
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "HEAD", "POST", "DELETE", "OPTIONS")
//                .maxAge(3600);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/swiper/**").addResourceLocations("file:D:\\buy_pics\\swiper\\");
        registry.addResourceHandler("/image/bigType/**").addResourceLocations("file:D:\\buy_pics\\bigType\\");
        registry.addResourceHandler("/image/product/**").addResourceLocations("file:D:\\buy_pics\\product\\");
        registry.addResourceHandler("/image/productSwiper/**").addResourceLocations("file:D:\\buy_pics\\productSwiper\\");
    }

    @Bean
    public SysInterceptor sysInterceptor() {
        return new SysInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = new String[]{
            "/product/**", "/wx/product/**", "/type/bigType/**",
            "/user/admin/login", "/wx/user/login", "/wx/user/list",
            "/wx/bigType/**", "/wx/smallType/**", "/order/**"
        };
        registry.addInterceptor(sysInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
