package com.commerce.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web项目配置类
 * @author commerce_小锋
 * @site www.commerce.com
 * @company 南通小锋网络科技有限公司
 * @create 2022-02-24 11:45
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Value("${avatarImagesFilePath}")
    private String avatarImagesFilePath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:8080")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE","OPTIONS")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + avatarImagesFilePath);
    }

}
