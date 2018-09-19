package com.xiaochen.middleware.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile(value = {"dev"})
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * UI页面显示信息
     */
    private final String SWAGGER2_API_BASEPACKAGE = "com.xiaochen.middleware.controller";
    private final String SWAGGER2_API_TITLE = "middleware-demo";
    private final String SWAGGER2_API_DESCRIPTION = "springboot构建中间件demo文档";
    private final String SWAGGER2_API_VERSION = "1.0.0";
    private final String CONTACT_NAME = "xiaochen";
    private final String CONTACT_URL = "www.xiaochen.com";
    private final String CONTACT_EMAIL = "misisipi@163.com";
    private final String TERMS_SERVICE_URL = "http://www.baidu.com/";
    private final String LICENSE= "Copyright 2017-2018 小臣";


    @Bean
    public Docket createRestfulApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER2_API_BASEPACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(SWAGGER2_API_TITLE)
                .description(SWAGGER2_API_DESCRIPTION)
                .termsOfServiceUrl(TERMS_SERVICE_URL)
                .contact(getContact())
                .license(LICENSE)
                .version(SWAGGER2_API_VERSION)
                .build();
    }

    private Contact getContact() {
        return new Contact(CONTACT_NAME,CONTACT_URL,CONTACT_EMAIL);
    }

}
