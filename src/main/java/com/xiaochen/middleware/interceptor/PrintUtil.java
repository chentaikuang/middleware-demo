package com.xiaochen.middleware.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PrintUtil {

    private static Logger logger = LoggerFactory.getLogger(PrintUtil.class);

    public static void params(HttpServletRequest request){

        requestInfo(request);

        printParams(request);
    }

    private static void printParams(HttpServletRequest request) {
        logger.info("\n");
        Map<String, String[]> paramsMap = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> itr = paramsMap.entrySet().iterator();
        int no = 1;
        while (itr.hasNext()){
            Map.Entry<String, String[]> entry = itr.next();
            logger.info("request.param{}. {}={}",(no++),entry.getKey(),entry.getValue()[0]);
        }
    }

    public static void result(HttpServletResponse response) {

    }

    /**
     * 打印request头信息
     * @param request
     */
    private static void requestInfo(HttpServletRequest request) {

        printUrl(request);

        printHeaders(request);
    }

    private static void printUrl(HttpServletRequest request) {
        logger.info("\n");
        logger.info("getRemoteAddr:{}", request.getRemoteAddr());
        logger.info("getRemoteHost:{}", request.getRemoteHost());
        logger.info("getRequestURI:{}", request.getRequestURI());
        logger.info("getRequestURL:{}", request.getRequestURL());
        logger.info("getServletPath:{}", request.getServletPath());
    }

    private static void printHeaders(HttpServletRequest request) {
        logger.info("\n");
        Enumeration<String> header = request.getHeaderNames();
        while (header.hasMoreElements()) {
            String headerNm = header.nextElement();
            logger.info(headerNm + ":" + request.getHeader(headerNm));
        }
    }
}