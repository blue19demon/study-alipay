package com.zk.alipay.request;

import java.io.Serializable;

/**
 * 对账单下载请求参数
 * @author Administrator
 *
 */
public class BillDownloadBizContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 类型
	 */
	private String bill_type;

	/**
	 * 对账日期
	 */
	private String bill_date;

	public String getBill_type() {
		return bill_type;
	}

	public BillDownloadBizContent setBill_type(String bill_type) {
		this.bill_type = bill_type;
		return this;
	}

	public String getBill_date() {
		return bill_date;
	}

	public BillDownloadBizContent setBill_date(String bill_date) {
		this.bill_date = bill_date;
		return this;
	}
	
}
