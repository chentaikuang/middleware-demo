package com.xiaochen.middleware.util;

/**
 * 缓存Key
 */
public class RedisKeyPrefix {

    /**
     * 商品信息
     */
    public static final String PRODUCT_DETAIL = "product:detail:";
    public static final String SYS_CONF = "sys_conf";

    private RedisKeyPrefix(){}
    /**
     * 商品KEY
     * @param productId
     * @return
     */
    public static String getProductKey(int productId){
        return PRODUCT_DETAIL + productId;
    }

}
