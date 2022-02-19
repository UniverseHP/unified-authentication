package com.universe.auth.thirdparty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 钉钉消息发生配置类
 * @desc 钉钉消息发生Https配置
 */
class DingDingMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public DingDingMappingJackson2HttpMessageConverter() {
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypes);
    }

}

/**
 * @author liheping
 * @desc 配置RestTemplate
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new DingDingMappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
