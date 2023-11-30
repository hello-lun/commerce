package com.commerce.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * MybatisPlus配置类
 * @author commerce_小锋
 * @site www.commerce.com
 * @company 南通小锋网络科技有限公司
 * @create 2022-01-30 8:10
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Configuration
    public static class KaptchaConfig {

        @Bean
        DefaultKaptcha producer() {
            Properties properties = new Properties();
            properties.put("kaptcha.border", "yes");
            properties.put("kaptcha.border.color", "gray");
            properties.put("kaptcha.textproducer.font.color", "blue");
            properties.put("kaptcha.textproducer.char.space", "4");
            properties.put("kaptcha.image.height", "40");
            properties.put("kaptcha.image.width", "120");
            properties.put("kaptcha.textproducer.font.size", "30");

            Config config = new Config(properties);
            DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
            defaultKaptcha.setConfig(config);

            return defaultKaptcha;
        }

    }
}
