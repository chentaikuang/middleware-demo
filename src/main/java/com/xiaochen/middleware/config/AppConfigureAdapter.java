package com.xiaochen.middleware.config;

import com.xiaochen.middleware.interceptor.ParamsLogIntercetor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableWebMvc
@Configuration
public class AppConfigureAdapter extends WebMvcConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.profiles.active}")
    private String active;

    @Bean
    public ParamsLogIntercetor getParamsIntercetor() {
        return new ParamsLogIntercetor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        logger.info("==>> system active:{}", active);
        registry.addInterceptor(getParamsIntercetor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
