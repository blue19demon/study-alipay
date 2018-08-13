package com.zk.alipay.response;

/**
 * 支付宝PC端交易查询响应参数
 * @author Administrator
 *
 */
public class TradeQueryResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商户网站唯一订单号
	 */
	private TradeQuerySubResponse alipay_trade_query_response;

	public TradeQuerySubResponse getAlipay_trade_query_response() {
		return alipay_trade_query_response;
	}

	public void setAlipay_trade_query_response(TradeQuerySubResponse alipay_trade_query_response) {
		this.alipay_trade_query_response = alipay_trade_query_response;
	}
}