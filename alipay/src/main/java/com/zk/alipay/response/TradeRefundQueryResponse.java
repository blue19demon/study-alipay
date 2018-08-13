package com.zk.alipay.response;

/**
 * 支付宝PC端退款查询响应参数
 * @author Administrator
 *
 */
public class TradeRefundQueryResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商户网站唯一订单号
	 */
	private TradeRefundQuerySubResponse alipay_trade_fastpay_refund_query_response;
	public TradeRefundQuerySubResponse getAlipay_trade_fastpay_refund_query_response() {
		return alipay_trade_fastpay_refund_query_response;
	}
	public void setAlipay_trade_fastpay_refund_query_response(
			TradeRefundQuerySubResponse alipay_trade_fastpay_refund_query_response) {
		this.alipay_trade_fastpay_refund_query_response = alipay_trade_fastpay_refund_query_response;
	}
}