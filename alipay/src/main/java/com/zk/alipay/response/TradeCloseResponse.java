package com.zk.alipay.response;

/**
 * 支付宝关闭交易响应参数
 * @author Administrator
 *
 */
public class TradeCloseResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商户网站唯一订单号
	 */
	private TradeCloseSubResponse alipay_trade_close_response;
	public TradeCloseSubResponse getAlipay_trade_close_response() {
		return alipay_trade_close_response;
	}
	public void setAlipay_trade_close_response(TradeCloseSubResponse alipay_trade_close_response) {
		this.alipay_trade_close_response = alipay_trade_close_response;
	}
	
}