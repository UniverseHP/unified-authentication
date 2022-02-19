package com.universe.auth.thirdparty.controller;


import com.universe.auth.common.utils.R;
import com.universe.auth.thirdparty.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送邮箱验证码接口
 *
 * @author: lhp
 * @date 2022/1/13 18:46
 */
@Slf4j
@RestController
@RequestMapping("/api/third/email")
public class EmailSendController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送邮件消息.
     *
     * @param email   邮箱
     * @param content 内容
     * @return R
     */
    @GetMapping("/sendMsg")
    public R sendEmailMsg(@RequestParam("email") String email,
                           @RequestParam("content") String content) {
        return R.ok().put("data", emailService.sendSimpleMail(email, content));
    }


}
