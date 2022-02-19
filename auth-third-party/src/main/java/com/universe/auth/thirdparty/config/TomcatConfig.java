package com.universe.auth.thirdparty.config;


import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 配置tomcat
 * @author: lhp
 * @time: 2022/1/17 10:42 上午
 */
@Configuration
public class TomcatConfig {
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers((Connector connector) -> {
            connector.setProperty("relaxedPathChars", "\"{\\}^`{|}");
            connector.setProperty("relaxedQueryChars", "\"{\\}^`{|}");
        });
        return tomcatServletWebServerFactory;
    }
}