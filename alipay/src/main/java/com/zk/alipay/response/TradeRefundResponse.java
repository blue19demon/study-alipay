package com.zk.alipay.response;

/**
 * 支付宝PC端退款响应参数
 * @author Administrator
 *
 */
public class TradeRefundResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商户网站唯一订单号
	 */
	private TradeRefundSubResponse alipay_trade_refund_response;
	public TradeRefundSubResponse getAlipay_trade_refund_response() {
		return alipay_trade_refund_response;
	}
	public void setAlipay_trade_refund_response(TradeRefundSubResponse alipay_trade_refund_response) {
		this.alipay_trade_refund_response = alipay_trade_refund_response;
	}

}