package com.universe.auth.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lhp
 * @desc 用户模块
 * @date 2021/9/24
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AuthMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthMemberApplication.class, args);
    }

}
