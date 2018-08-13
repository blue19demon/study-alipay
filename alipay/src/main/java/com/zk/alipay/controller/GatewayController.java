package com.zk.alipay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.zk.alipay.config.AlipayConfig;
import com.zk.alipay.config.ProductCodeConfig;
import com.zk.alipay.request.PayBizContent;

/**
 * 网关接口
 * @author Administrator
 *
 */
@RestController
public class GatewayController {
	
	@Autowired
	private AlipayConfig alipayConfig;
	/**
	 * PC普通付款
	 * @param WIDout_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
	 * @param WIDtotal_amount 付款金额，必填
	 * @param WIDsubject 订单名称，必填
	 * @param WIDbody 商品描述，可空
	 * @return
	 */
	@PostMapping("/fastInstantTadePay.do")
	public String fastInstantTadePay(
			String WIDout_trade_no,
			String WIDtotal_amount,
			String WIDsubject,
			String WIDbody) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
			alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
			PayBizContent bizContentVO =
					new PayBizContent(ProductCodeConfig.FAST_INSTANT_TRADE_PAY)
					.setOut_trade_no(WIDout_trade_no)
					.setTotal_amount(WIDtotal_amount)
					.setSubject(WIDsubject)
					.setBody(WIDbody)
					.setTimeout_express("1m");
			alipayRequest.setBizContent(JSONObject.toJSONString(bizContentVO));
			//请求
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			//输出
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * wap普通付款
	 * @param WIDout_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
	 * @param WIDtotal_amount 付款金额，必填
	 * @param WIDsubject 订单名称，必填
	 * @param WIDbody 商品描述，可空
	 * @return
	 */
	@PostMapping("/quickWapWay.do")
	public String quickWapWay(
			String WIDout_trade_no,
			String WIDtotal_amount,
			String WIDsubject) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
			//设置请求参数
		    AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
			alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
			alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
			PayBizContent bizContentVO =
					new PayBizContent(ProductCodeConfig.QUICK_WAP_WAY)
					.setOut_trade_no(WIDout_trade_no)
					.setTotal_amount(WIDtotal_amount)
					.setSubject(WIDsubject)
					.setTimeout_express("1m");
			alipayRequest.setBizContent(JSONObject.toJSONString(bizContentVO));
			//请求
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			//输出
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 二维码付款
	 * @param WIDout_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
	 * @param WIDtotal_amount 付款金额，必填
	 * @param WIDsubject 订单名称，必填
	 * @param WIDbody 商品描述，可空
	 * @return
	 */
	@PostMapping("/precreateTadePay.do")
	public String precreateTadePay(
			String WIDout_trade_no,
			String WIDtotal_amount,
			String WIDsubject,
			String WIDbody) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
			//设置请求参数
			AlipayTradePrecreateRequest  alipayRequest = new AlipayTradePrecreateRequest ();
			alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
			alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
			PayBizContent bizContentVO =
					new PayBizContent(ProductCodeConfig.FAST_INSTANT_TRADE_PAY)
					.setOut_trade_no(WIDout_trade_no)
					.setTotal_amount(WIDtotal_amount)
					.setSubject(WIDsubject)
					.setBody(WIDbody);
			alipayRequest.setBizContent(JSONObject.toJSONString(bizContentVO));
			//请求
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			//输出
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
