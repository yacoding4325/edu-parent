package com.javaclimb.service.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 4:36
 */
//配置文件
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 分组名
                .groupName("WebApi")
                .apiInfo(apiInfo())
                .select()
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Java攀登网API文档")
                .description("描述服务端的 api 接口定义")
                .version("1.0")
                .contact(new Contact("javaclimb", "http://www.javaclimb.com", "xxxx.@xxx.com"))
                .build();
    }

}