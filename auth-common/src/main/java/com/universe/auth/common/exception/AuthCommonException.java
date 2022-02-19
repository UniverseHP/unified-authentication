package com.universe.auth.common.exception;


import lombok.Data;

/**
 * @author mlhp1
 */
@Data
public class AuthCommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**
     * 异常消息内容
     */
    private String msg;

    /**
     * 异常信息状态码
     * 默认错误状态码 902:系统错误
     */
    private int code = BizCodeEnum.ERROR_SYSTEM.getCode();

    public AuthCommonException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AuthCommonException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMsg());
        this.msg = bizCodeEnum.getMsg();
        this.code = bizCodeEnum.getCode();
    }

    public AuthCommonException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

}
