package com.zk.alipay.request;

import java.io.Serializable;

/**
 * 支付宝PC端交易查询请求参数
 * @author Administrator
 *
 */
public class TradeQueryBizContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商户网站唯一订单号
	 */
	private String out_trade_no;
	/**
	 * 支付宝唯一订单号
	 */
	private String trade_no;
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public TradeQueryBizContent(String out_trade_no, String trade_no) {
		super();
		this.out_trade_no = out_trade_no;
		this.trade_no = trade_no;
	}
	public TradeQueryBizContent() {
		super();
	}
	
}
