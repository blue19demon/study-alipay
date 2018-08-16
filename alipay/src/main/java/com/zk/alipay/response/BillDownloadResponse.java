package com.zk.alipay.response;

import java.io.Serializable;

/**
 * 对账文件下载
 * @author Administrator
 *
 */
public class BillDownloadResponse implements Serializable {

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
	private BillDownloadSubResponse alipay_data_dataservice_bill_downloadurl_query_response;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public BillDownloadSubResponse getAlipay_data_dataservice_bill_downloadurl_query_response() {
		return alipay_data_dataservice_bill_downloadurl_query_response;
	}
	public void setAlipay_data_dataservice_bill_downloadurl_query_response(
			BillDownloadSubResponse alipay_data_dataservice_bill_downloadurl_query_response) {
		this.alipay_data_dataservice_bill_downloadurl_query_response = alipay_data_dataservice_bill_downloadurl_query_response;
	}
	
}
