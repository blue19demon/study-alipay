package com.zk.alipay.request;

import java.io.Serializable;

/**
 * 退款查询
 * @author Administrator
 *
 */
public class TradeRefundQueryBizContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商户订单号
	 */
	private String out_trade_no;
	/**
	 * 支付宝交易号
	 */
	private String trade_no;
	/**
	 * 退款请求号
	 */
	private String out_request_no;
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public TradeRefundQueryBizContent setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public TradeRefundQueryBizContent setTrade_no(String trade_no) {
		this.trade_no = trade_no;
		return this;
	}
	public String getOut_request_no() {
		return out_request_no;
	}
	public TradeRefundQueryBizContent setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
		return this;
	}
	

}
