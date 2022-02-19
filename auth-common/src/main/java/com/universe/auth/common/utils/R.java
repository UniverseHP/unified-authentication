package com.universe.auth.common.utils;


import com.alibaba.fastjson.JSON;
import com.universe.auth.common.exception.BizCodeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhp
 * @desc 接口返回信息类
 * @Date: 2022/02/19
 */
public class R extends HashMap<String, Object> {
    /**
     * 序列化编号
     */
    private static final long serialVersionUID = 1L;

    public R() {
        // 默认状态码为 1000, "正常执行"
        put("code", BizCodeEnum.SUCCESS_CODE.getCode());
    }

    public static R error() {
        return error(BizCodeEnum.ERROR_SYSTEM.getCode(), BizCodeEnum.ERROR_SYSTEM.getMsg());
    }

    public static R error(String msg) {
        return error(BizCodeEnum.ERROR_SYSTEM.getCode(), msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R noExpect(BizCodeEnum bizCodeEnum) {
        R r = new R();
        r.put("code", bizCodeEnum.getCode());
        r.put("msg", bizCodeEnum.getMsg());
        return r;
    }

    public static R noExpect(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {
        return (Integer) this.get("code");
    }

    /**
     * @param key 获取指定key的名字
     */
    public <T> T getData(String key, Class<T> tClass) {
        // get("data") 默认是map类型 所以再由map转成string再转json
        Object data = get(key);
        return JSON.parseObject(JSON.toJSONString(data), tClass);
    }

    /**
     * @param key 获取指定key的名字
     */
    public <T> List<T> getArrayData(String key, Class<T> tClass) {
        // get("data") 默认是map类型 所以再由map转成string再转json
        Object data = get(key);
        return JSON.parseArray(JSON.toJSONString(data), tClass);
    }


}
