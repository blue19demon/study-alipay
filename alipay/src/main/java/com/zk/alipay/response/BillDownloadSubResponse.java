package com.zk.alipay.response;

import java.io.Serializable;

/**
 * 对账文件下载
 * @author Administrator
 *
 */
public class BillDownloadSubResponse implements Serializable {

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
	 * 业务返回码，参见具体的API接口文档
	 */
	private String sub_code;

	/**
	 * 业务返回码描述，参见具体的API接口文档
	 */
	private String sub_msg;

	/**
	 * 账单下载地址链接，获取连接后30秒后未下载，链接地址失效。
	 */
	private String bill_download_url;

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

	public String getBill_download_url() {
		return bill_download_url;
	}

	public void setBill_download_url(String bill_download_url) {
		this.bill_download_url = bill_download_url;
	}

	public String getSub_code() {
		return sub_code;
	}

	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}

	public String getSub_msg() {
		return sub_msg;
	}

	public void setSub_msg(String sub_msg) {
		this.sub_msg = sub_msg;
	}
	
	
	
}
