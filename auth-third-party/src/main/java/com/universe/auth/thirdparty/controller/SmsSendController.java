package com.universe.auth.thirdparty.controller;


import com.universe.auth.common.utils.R;
import com.universe.auth.thirdparty.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送短信验证码接口
 *
 * @author: lhp
 * @date 2022/1/13 18:46
 */
@RestController
@RequestMapping("/api/third/sms")
public class SmsSendController {

    @Autowired
    private SmsService smsService;

    /**
     * 发送短信消息.
     *
     * @param mobile  手机号
     * @param content 内容
     * @return R
     */
    @GetMapping("/sendMsg")
    public R sendSmsMsg(@RequestParam("mobile") String mobile,
                         @RequestParam("content") String content) {
        return R.ok().put("data", smsService.sendSmsCode(mobile, content));
    }
}
