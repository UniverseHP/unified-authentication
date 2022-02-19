package com.universe.auth.thirdparty.service.impl;

import com.whaleal.icefrog.core.util.StrUtil;
import com.universe.auth.thirdparty.service.DingDingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @user lhp
 * @description 钉钉告警
 * @date 2022/1/13 18:45
 */
@Service
@Slf4j
public class DingDingServiceImpl implements DingDingService {

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 钉钉机器人发信链接前缀
     */
    private final static String BASE_URL = "https://oapi.dingtalk.com/robot/send?";
    /**
     * 钉钉机器人发信模板
     */
    private final static String MESSAGE = "{\"at\":{\"isAtAll\":false},\"text\":{\"content\":\"xxxxx\"},\"msgtype\":\"text\"}";

    @Override
    public String sendAlertMsg(String accessToken, String secret, String content) {
        StringBuilder url = new StringBuilder().append(BASE_URL);
        url.append("access_token=").append(accessToken);
        // 有些机器人不需要进行配置签名
        if (StrUtil.isNotBlank(secret)) {
            // 签名不为空时 则需要创建sign标识符拼接到url上
            long timestamp = System.currentTimeMillis();
            url.append("&timestamp=").append(timestamp);
            url.append("&sign=").append(createSign(timestamp, secret));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("charset", "utf-8");
        // 替换模板中空缺信息
        HttpEntity<Object> entity = new HttpEntity<>(MESSAGE.replace("xxxxx", content), headers);
        ResponseEntity<Object> exchange = restTemplate.exchange(url.toString(), HttpMethod.POST, entity, Object.class);
        boolean xxSuccessful = exchange.getStatusCode().is2xxSuccessful();
        return exchange.getBody().toString();
    }

    /**
     * 生成Sign
     *
     * @param timestamp 时间戳
     * @param secret    签名
     * @return sign 标识符
     */
    private static String createSign(Long timestamp, String secret) {
        try {
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        } catch (Exception e) {
            log.error("创建钉钉Sign失败:{}", e.getMessage());
        }
        return "";
    }

}
