package com.universe.auth.thirdparty.controller;

import com.universe.auth.common.utils.R;
import com.universe.auth.thirdparty.service.DingDingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @user lhp
 * @description 钉钉发信接口
 * @date 2022/1/13 18:46
 */
@Slf4j
@RestController
@RequestMapping("api/third/ding")
public class DingDingSendController {

    @Autowired
    private DingDingService dingDingService;

    /**
     * 发送钉钉消息
     *
     * @param accessToken 密钥
     * @param secret      签名
     * @param content     内容
     * @return R
     */
    @GetMapping("/sendMsg")
    public R sendDingDingMsg(@RequestParam("accessToken") String accessToken,
                             @RequestParam("secret") String secret,
                             @RequestParam("content") String content) {
        return R.ok().put("data", dingDingService.sendAlertMsg(accessToken, secret, content));
    }

}
