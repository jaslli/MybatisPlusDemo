package com.yww.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * springdoc-openapi的配置文件
 * </p>
 *
 * @ClassName openapiConfig
 * @Author yww
 * @Date 2022/10/16 6:55
 */
@Configuration
public class OpenapiConfig {

    /**
     * springdoc-openapi的总体配置
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI();
    }

    /**
     * RBAC系统接口
     */
    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("RBAC的系统接口")
                .packagesToScan("com.yww.demo.controller")
                .build();
    }
}
