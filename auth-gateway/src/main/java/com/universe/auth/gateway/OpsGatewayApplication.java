package com.universe.auth.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lhp
 * @desc 网关模块
 * @date 2021/9/24
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OpsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpsGatewayApplication.class, args);
    }

}
