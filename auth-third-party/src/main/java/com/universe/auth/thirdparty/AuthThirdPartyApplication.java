package com.universe.auth.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author lhp
 * @desc: 第三方发信信息类
 * @date 2021/9/27
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AuthThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthThirdPartyApplication.class, args);
    }
}
