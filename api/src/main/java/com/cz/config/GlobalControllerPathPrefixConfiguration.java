package com.cz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 为 Controller 接口配置统一前缀
 *
 */
@Configuration
public class GlobalControllerPathPrefixConfiguration implements WebMvcConfigurer {

    @Value("${spring.controller.path-prefix:}")
    private String pathPrefix;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.addPathPrefix(pathPrefix, c -> c.isAnnotationPresent(RestController.class));
    }
}
