package com.zk.alipay.request;

import java.io.Serializable;

/**
 * 退款申请
 * @author Administrator
 *
 */
public class TradeRefundBizContent implements Serializable {

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
	 * 退款金额
	 */
	private String refund_amount;
	/**
	 * 退款原因
	 */
	private String refund_reason;
	/**
	 * 退款请求号
	 */
	private String out_request_no;

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public TradeRefundBizContent setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public TradeRefundBizContent setTrade_no(String trade_no) {
		this.trade_no = trade_no;
		return this;

	}

	public String getRefund_amount() {
		return refund_amount;
	}

	public TradeRefundBizContent setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
		return this;
	}

	public String getRefund_reason() {
		return refund_reason;
	}

	public TradeRefundBizContent setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
		return this;
	}

	public String getOut_request_no() {
		return out_request_no;
	}

	public TradeRefundBizContent setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
		return this;
	}

}
