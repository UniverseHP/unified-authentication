package com.universe.auth.common.exception;


/**
 * 公共异常错误信息表
 * 错误码列表：
 * 9:   系统 。开头的code的msg不进行前端展示
 * 10 ：公共 无msg
 * 11: 用户
 * 12: 团队
 *
 * @author mlhp1
 */
public enum BizCodeEnum {
    /**
     * 系统未知异常.
     */
    UNKNOWN_EXCEPTION(901, "系统未知异常"),
    /**
     * 系统错误.
     */
    ERROR_SYSTEM(902, "系统错误"),
    /**
     * 网关限流.
     */
    LIMIT_GATEWAY(903, "网关限流"),
    /**
     * 正常执行.
     */
    SUCCESS_CODE(1000, "正常执行"),
    /**
     * 更新信息失败.
     */
    ERROR_UPDATE_MEMBER(1001, "更新信息失败"),
    /**
     * 数据不存在.
     */
    NOT_EXIST_DATA(1003, "数据不存在"),
    /**
     * 账号不存在.
     */
    NOT_EXIST_ACCOUNT(1101, "账号不存在"),
    /**
     * 密码错误.
     */
    ERROR_PASSWORD(1102, "密码错误"),
    /**
     * 账号不可为空.
     */
    BLANK_ACCOUNT(1103, "账号不可为空"),
    /**
     * 手机号已存在.
     */
    EXIST_PHONE(1104, "手机号已存在"),
    /**
     * 邮箱已存在.
     */
    EXIST_EMAIL(1105, "邮箱已存在"),
    /**
     * 账号已存在.
     */
    EXIST_ACCOUNT(1106, "账号已存在"),
    /**
     * TOKEN不存在.
     */
    NOT_EXIST_TOKEN(1107, "TOKEN不存在");

    /**
     * 状态.
     */
    private int code;
    /**
     * 消息.
     */
    private String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取状态
     *
     * @return 状态
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取消息
     *
     * @return 消息
     */
    public String getMsg() {
        return msg;
    }
}
