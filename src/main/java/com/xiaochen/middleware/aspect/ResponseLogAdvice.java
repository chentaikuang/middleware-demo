package com.xiaochen.middleware.aspect;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xiaochen.middleware.interceptor.PrintUtil;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器响应结果日志打印
 */
@Aspect
@Component
@ControllerAdvice(basePackages = "com.xiaochen.middleware.controller")
public class ResponseLogAdvice implements ResponseBodyAdvice<Object> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
//        logger.info("returnType:{}",JSONObject.toJSONString(returnType));
//        logger.info("converterType:{}",JSONObject.toJSONString(converterType));
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //logger.info("request.url:{}", request.getURI().toString());

        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        PrintUtil.params(servletRequest);
        logger.info("\nresponse.body:{}", JSONObject.toJSONString(body));
//        logger.info("returnType:{}",JSONObject.toJSONString(returnType));
//        logger.info("selectedContentType:{}",JSONObject.toJSONString(selectedContentType));
//        logger.info("selectedConverterType:{}",JSONObject.toJSONString(selectedConverterType));
        return body;
    }
}
