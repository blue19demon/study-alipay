package com.zk.alipay.config;

import org.springframework.context.annotation.Configuration;

/**
 * 接口名称
 * 
 * @author Administrator
 *
 */
@Configuration
public class ProductCodeConfig {

	/**
	 * PC端付款接口
	 */
	public static String FAST_INSTANT_TRADE_PAY = "FAST_INSTANT_TRADE_PAY";
	/**
	 * WAP端付款接口
	 */
	public static String QUICK_WAP_WAY = "QUICK_WAP_WAY";

}
