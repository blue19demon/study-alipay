package com.zk.alipay.response;

import java.io.Serializable;

/**
 * 退款查询
 * @author Administrator
 *
 */
public class TradeRefundQuerySubResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 网关返回码
	 */
	private String code;

	/**
	 * 网关返回码描述
	 */
	private String msg;
	/**
	 * 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
	 */
	private String out_request_no;
	/**
	 * 	订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
	 */
	private String out_trade_no;
	/**
	 * 本次退款请求，对应的退款金额
	 */
	private String refund_amount;
	/**
	 * 该笔退款所对应的交易的订单金额
	 */
	private String total_amount;
	/**
	 * 支付宝交易号，和商户订单号不能同时为空
	 */
	private String trade_no;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getOut_request_no() {
		return out_request_no;
	}
	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	
	
	
}
