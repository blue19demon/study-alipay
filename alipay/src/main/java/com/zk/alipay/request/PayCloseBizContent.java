package com.zk.alipay.request;

import java.io.Serializable;

/**
 * 交 易 关 闭
 * @author Administrator
 *
 */
public class PayCloseBizContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商户网站唯一订单号
	 */
	private String out_trade_no;
	/**
	 * 支付宝交易号
	 */
	private String trade_no;
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public PayCloseBizContent setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public PayCloseBizContent setTrade_no(String trade_no) {
		this.trade_no = trade_no;
		return this;
	}
}
