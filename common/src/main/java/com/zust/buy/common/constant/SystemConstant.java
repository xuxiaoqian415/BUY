package com.zust.buy.common.constant;

/**
 * 系统级静态变量
 */
public class SystemConstant {

    /**
     * Token不存在
     */
    public static final int JWT_ERRCODE_NULL = 4000;

    /**
     * Token过期
     */
    public static final int JWT_ERRCODE_EXPIRE = 4001;

    /**
     * Token验证不通过
     */
    public static final int JWT_ERRCODE_FAIL = 4002;

    /**
     * JWT密匙
     */
//    public static final String JWT_SECERT = "8677df7fc3a34e26a61c034d5ec8245d";
    public static final String JWT_SECERT = "1065df1fc3a29e26b61c074d5ac8645d";

    /**
     * Token有效时间
     */
    public static final long JWT_TTL = 24 * 60 * 60 * 1000;
}
