package com.universe.auth.gateway.exception;


import com.universe.auth.common.exception.AuthCommonException;
import com.universe.auth.common.exception.BizCodeEnum;
import com.universe.auth.common.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局错误信息进行拦截
 *
 * @author lhp
 * @desc 全局错误信息进行拦截
 * @date 2021 /10/20
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局拦截.AuthCommonException
     *
     * @param e the e
     * @return the r
     */
    @ExceptionHandler(AuthCommonException.class)
    @ResponseBody
    public R error(AuthCommonException e) {
        e.printStackTrace();
        return R.error().put("msg", e.getMsg()).put("code", e.getCode());
    }

    /**
     * 全局拦截Exception异常
     *
     * @param e the e
     * @return the r
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().put("msg", e.getMessage()).put("code", BizCodeEnum.ERROR_SYSTEM.getCode());
    }

}
