package com.universe.auth.thirdparty.service;



/**
 * @user lhp
 * @description 钉钉发信Server
 * @date 2022/1/13 18:45
 */
public interface DingDingService {

    /**
     * 发送钉钉警告信息
     *
     * @param accessToken accessToken
     * @param secret      secret
     * @param content     内容
     * @return 钉钉接口的返回信息
     */
    String sendAlertMsg(String accessToken, String secret, String content);
}
