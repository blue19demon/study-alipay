package com.zk.alipay.request;

import java.io.Serializable;

/**
 * 支付宝PC端交易请求参数
 * @author Administrator
 *
 */
public class PayBizContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商户网站唯一订单号
	 */
	private String out_trade_no;
	/**
	 * 金额
	 */
	private String total_amount;
	/**
	 * 商品的标题/交易标题/订单标题/订单关键字等。
	 */
	private String subject;
	/**
	 * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
	 */
	private String body;
	/**
	 * 销售产品码，商家和支付宝签约的产品码。
	 */
	private String product_code;
	/**
	 * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
该参数在请求到支付宝时开始计时。
	 */
	private String timeout_express;
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public PayBizContent setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public PayBizContent setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
		return this;
	}
	public String getSubject() {
		return subject;
	}
	public PayBizContent setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public String getBody() {
		return body;
	}
	public PayBizContent setBody(String body) {
		this.body = body;
		return this;
	}
	public String getProduct_code() {
		return product_code;
	}
	public PayBizContent setProduct_code(String product_code) {
		this.product_code = product_code;
		return this;
	}
	
	public String getTimeout_express() {
		return timeout_express;
	}
	public PayBizContent setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
		return this;
	}
	public PayBizContent(String product_code) {
		super();
		this.product_code = product_code;
	}
	public PayBizContent() {
		super();
	}
}
