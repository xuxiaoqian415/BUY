package com.zust.buy.common.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面响应entity
 */
public class ResponseData extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public ResponseData() {
        put("code", 200);
    }

    public ResponseData(Object data) {
        put("code", 200);
        put("msg", "success");
        put("result", data);
    }

    public static ResponseData error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static ResponseData error(String msg) {
        return error(500, msg);
    }

    public static ResponseData error(int code, String msg) {
        ResponseData responseData = new ResponseData();
        responseData.put("code", code);
        responseData.put("msg", msg);
        return responseData;
    }

    public static ResponseData ok(Map<String, Object> map) {
        return new ResponseData(map);
    }

    public static ResponseData ok(Object data) {
        return new ResponseData(data);
    }

    public static ResponseData ok() {
        return new ResponseData();
    }

    public ResponseData put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
