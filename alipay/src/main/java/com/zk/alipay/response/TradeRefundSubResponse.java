package com.zk.alipay.response;

import java.io.Serializable;

/**
 * 退款申请
 * @author Administrator
 *
 */
public class TradeRefundSubResponse implements Serializable {
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
	 * 支付宝交易号
	 */
	private String trade_no;
	/**
	 * 商家订单号
	 */
	private String out_trade_no;
	/**
	 * 买家支付宝账号
	 */
	private String buyer_logon_id;
	/**
	 * 本次退款是否发生了资金变化
	 */
	private String fund_change;
	/**
	 * 买家在支付宝的用户id
	 */
	private String buyer_user_id;
	/**
	 * 退款总金额
	 */
	private String refund_fee;

	/**
	 * 积分支付的金额
	 */
	private String send_back_fee;
	/**
	 * 退款支付时间
	 */
	private String gmt_refund_pay;
	
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
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}
	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}
	public String getFund_change() {
		return fund_change;
	}
	public void setFund_change(String fund_change) {
		this.fund_change = fund_change;
	}
	public String getBuyer_user_id() {
		return buyer_user_id;
	}
	public void setBuyer_user_id(String buyer_user_id) {
		this.buyer_user_id = buyer_user_id;
	}
	
	public String getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getSend_back_fee() {
		return send_back_fee;
	}
	public void setSend_back_fee(String send_back_fee) {
		this.send_back_fee = send_back_fee;
	}
	public String getGmt_refund_pay() {
		return gmt_refund_pay;
	}
	public void setGmt_refund_pay(String gmt_refund_pay) {
		this.gmt_refund_pay = gmt_refund_pay;
	}
	
}
