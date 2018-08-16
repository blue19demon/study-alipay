package com.zk.alipay.response;

import java.io.Serializable;

/**
 * 提现返回结果
 * 
 * @author Administrator
 *
 */
public class AlipayTransferResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 响应实体
	 */
	private AlipayTransferSubResponse alipay_fund_trans_toaccount_transfer_response;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public AlipayTransferSubResponse getAlipay_fund_trans_toaccount_transfer_response() {
		return alipay_fund_trans_toaccount_transfer_response;
	}
	public void setAlipay_fund_trans_toaccount_transfer_response(
			AlipayTransferSubResponse alipay_fund_trans_toaccount_transfer_response) {
		this.alipay_fund_trans_toaccount_transfer_response = alipay_fund_trans_toaccount_transfer_response;
	}
	
}
