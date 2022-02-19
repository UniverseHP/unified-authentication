package com.universe.auth.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.universe.auth.common.exception.BizCodeEnum;
import com.universe.auth.common.exception.AuthCommonException;
import com.universe.auth.thirdparty.service.SmsService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 邮件发送接口实现类
 * @author: lhp
 * @time: 2022/1/13 4:58 下午
 */
@Service
@Slf4j
@ToString
public class SmsServiceImpl implements SmsService {

    @Override
    public String sendSmsCode(String mobile, String content) {
        return "发送成功:" + content;
    }
}
