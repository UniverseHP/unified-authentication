package com.universe.auth.thirdparty.service;

import java.io.IOException;

/**
 * @description: 短信发信Server
 * @author: lhp
 * @time: 2022/1/13 4:57 下午
 */
public interface SmsService {
    /**
     * 发送邮箱验证码
     *
     * @param mobile 手机号
     * @param content  内容
     * @return String
     */
    String sendSmsCode(String mobile, String content);
}
