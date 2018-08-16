package com.zk.alipay.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.zk.alipay.config.AlipayConfig;
import com.zk.alipay.request.BillDownloadBizContent;
import com.zk.alipay.request.PayCloseBizContent;
import com.zk.alipay.request.TradeQueryBizContent;
import com.zk.alipay.request.TradeRefundBizContent;
import com.zk.alipay.request.TradeRefundQueryBizContent;
import com.zk.alipay.response.AlipayTransferResponse;
import com.zk.alipay.response.BillDownloadResponse;
import com.zk.alipay.response.TradeCloseResponse;
import com.zk.alipay.response.TradeQueryResponse;
import com.zk.alipay.response.TradeRefundQueryResponse;
import com.zk.alipay.response.TradeRefundResponse;

/**
 * @author Administrator
 *
 */
@Controller
public class APIController {

	@Autowired
	private AlipayConfig alipayConfig;

	@GetMapping("")
	public String index() {
		return "index";
	}

	/**
	 * 页面通知地址
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/returnUrl.htm")
	public String returnUrl(HttpServletRequest request, Model model) {
		try {
			// 获取支付宝GET过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			boolean signVerified = AlipaySignature.rsaCheckV1(
					params, 
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getCharset(), 
					alipayConfig.getSign_type()); // 调用SDK验证签名
			// ——请在这里编写您的程序（以下代码仅作参考）——
			if (signVerified) {
				// 商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 付款金额
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

				model.addAttribute("trade_no", trade_no);
				model.addAttribute("out_trade_no", out_trade_no);
				model.addAttribute("total_amount", total_amount);
			} else {
				System.out.println("验签失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "return_url";
	}
	
	
	/**
	 * 交 易 查 询
	 * @param WIDTQout_trade_no 商户订单号
	 * @param WIDTQtrade_no 支付宝交易号
	 * @return
	 */
	@PostMapping("/tradeQuery.do")
	public String tradeQuery(
			String WIDTQout_trade_no,
			String WIDTQtrade_no,
			Model model) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
			
			//设置请求参数
			AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
			//请二选一设置
			TradeQueryBizContent bizContentTradeQuery=new TradeQueryBizContent(WIDTQout_trade_no, WIDTQtrade_no);
			alipayRequest.setBizContent(JSONObject.toJSONString(bizContentTradeQuery));
			//请求
			String result = alipayClient.execute(alipayRequest).getBody();
			//输出
			TradeQueryResponse bizContentTradeQueryResponse=JSONObject.parseObject(result, TradeQueryResponse.class);
			model.addAttribute("bizContentTradeQueryResponse", bizContentTradeQueryResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "query_result";
	}
	
	
	
	/**
	 * 退 款
	 * @param WIDTRout_trade_no 商户订单号
	 * @param WIDTRtrade_no 支付宝交易号
	 * @param WIDTRrefund_amount 退款金额
	 * @param WIDTRrefund_reason 退款原因
	 * @param WIDTRout_request_no 退款请求号
	 * @param model
	 * @return
	 */
	@PostMapping("/tradeRefund.do")
	public String tradeRefund(
			String WIDTRout_trade_no,
			String WIDTRtrade_no,
			String WIDTRrefund_amount,
			String WIDTRrefund_reason,
			String WIDTRout_request_no,
			Model model) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
		
			//设置请求参数
			AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
			TradeRefundBizContent alipayTradeRefundBizContent=
					new TradeRefundBizContent()
					.setOut_trade_no(WIDTRout_trade_no)
					.setTrade_no(WIDTRtrade_no)
					.setRefund_amount(WIDTRrefund_amount)
					.setRefund_reason(WIDTRrefund_reason)
					.setOut_request_no(WIDTRout_request_no);
			alipayRequest.setBizContent(JSONObject.toJSONString(alipayTradeRefundBizContent));
			
			//请求
			String result = alipayClient.execute(alipayRequest).getBody();
			//输出
			System.out.println(result);
			TradeRefundResponse tradeRefundResponse=JSONObject.parseObject(result, TradeRefundResponse.class);
			model.addAttribute("tradeRefundResponse", tradeRefundResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trade_refund";
	}
	
	/**
	 * 退 款 查 询
	 * @param WIDTRout_trade_no 商户订单号
	 * @param WIDTRtrade_no 支付宝交易号
	 * @param WIDRQout_request_no 退款请求流水号
	 * @param model
	 * @return
	 */
	@PostMapping("/tradeRefundQuery.do")
	public String tradeRefund(
			String WIDRQout_trade_no,
			String WIDTRtrade_no,
			String WIDRQout_request_no,
			Model model) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
		
			//设置请求参数
			AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
			TradeRefundQueryBizContent alipayTradeRefundBizContent=
					new TradeRefundQueryBizContent()
					.setOut_trade_no(WIDRQout_trade_no)
					.setTrade_no(WIDTRtrade_no)
					.setOut_request_no(WIDRQout_request_no);
			alipayRequest.setBizContent(JSONObject.toJSONString(alipayTradeRefundBizContent));
			
			//请求
			String result = alipayClient.execute(alipayRequest).getBody();
			//输出
			System.out.println(result);
			TradeRefundQueryResponse tradeRefundQueryResponse=JSONObject.parseObject(result, TradeRefundQueryResponse.class);
			model.addAttribute("tradeRefundQueryResponse", tradeRefundQueryResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "refund_query";
	}
	
	/**
	 * 交 易 关 闭（用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。）
	 * @param WIDTCout_trade_no 商户订单号
	 * @param WIDTCtrade_no 支付宝交易号
	 * @param model
	 * @return
	 */
	@PostMapping("/tradeClose.do")
	public String tradeClose(
			String WIDTCout_trade_no,
			String WIDTCtrade_no,
			Model model) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
		
			//设置请求参数
			AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
			
			//请二选一设置
			PayCloseBizContent payCloseBizContent = 
					new PayCloseBizContent()
					.setOut_trade_no(WIDTCout_trade_no)
					.setTrade_no(WIDTCtrade_no);
			alipayRequest.setBizContent(JSONObject.toJSONString(payCloseBizContent));
			
			//请求
			String result = alipayClient.execute(alipayRequest).getBody();
			
			//输出
			System.out.println(result);
			TradeCloseResponse tradeCloseResponse=JSONObject.parseObject(result, TradeCloseResponse.class);
			model.addAttribute("tradeCloseResponse", tradeCloseResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trade_close";
	}
	
	/**
	 * 交 易 撤 销（支付交易返回失败或支付系统超时，调用该接口撤销交易。如果此订单用户支付失败，支付宝系统会将此订单关闭；如果用户支付成功，支付宝系统会将此订单资金退还给用户。 注意：只有发生支付系统超时或者支付结果未知时可调用撤销，其他正常支付的单如需实现相同功能请调用申请退款API。提交支付交易后调用【查询订单API】，没有明确的支付结果再调用【撤销订单API】。）
	 * @param WIDTCout_trade_no 商户订单号
	 * @param WIDTCtrade_no 支付宝交易号
	 * @param model
	 * @return
	 */
	@PostMapping("/tradeCancel.do")
	public String tradeCancel(
			String WIDTCout_trade_no,
			String WIDTCtrade_no,
			Model model) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
		
			//设置请求参数
			AlipayTradeCancelRequest  alipayRequest = new AlipayTradeCancelRequest();
			
			//请二选一设置
			PayCloseBizContent payCloseBizContent = 
					new PayCloseBizContent()
					.setOut_trade_no(WIDTCout_trade_no)
					.setTrade_no(WIDTCtrade_no);
			alipayRequest.setBizContent(JSONObject.toJSONString(payCloseBizContent));
			
			//请求
			String result = alipayClient.execute(alipayRequest).getBody();
			
			//输出
			System.out.println(result);
			TradeCloseResponse tradeCloseResponse=JSONObject.parseObject(result, TradeCloseResponse.class);
			model.addAttribute("tradeCancelResponse", tradeCloseResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trade_cancel";
	}
	

	/**
	 * 单笔转账到支付宝账户接口是基于支付宝的资金处理能力，为了满足支付宝商家向其他支付宝账户转账的需求，
	 *  针对有部分开发能力的商家，提供通过API接口完成支付宝账户间的转账的功能。
	 *  该接口适用行业较广，比如商家间的货款结算，商家给个人用户发放佣金等。
	 * @param amount 提现金额
	 * @param payerShowName 付款方姓名
	 * @param payeeAccount 收款方账户
	 * @param payerRealName 收款方真实姓名
	 * @param remark 转账备注（支持200个英文/100个汉字）。
           当付款方为企业账户，且转账金额达到（大于等于）50000元，remark不能为空。收款方可见，会展示在收款用户的收支详情中。
	 * @param model 
	 * @return
	 */
	@PostMapping("/transfer.do")
	public String transfer(
			String amount,
			String payerShowName,
			String payeeAccount,
			String payerRealName,
			String remark, 
			Model model) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
		
			AlipayFundTransToaccountTransferModel data = new AlipayFundTransToaccountTransferModel();
			data.setOutBizNo(new Date().getTime()+"");//生成订单号

			// 收款方账户类型。可取值：
			// 1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
			// 2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。
			data.setPayeeType("ALIPAY_LOGONID");//固定值
			data.setPayeeAccount(payeeAccount);//转账收款账户
			data.setAmount(amount);
			data.setPayerShowName(payerShowName);
			data.setPayerRealName(payerRealName);//账户真实名称
		    data.setRemark(remark);
		    Map<String,Object> isSuccess=transfer(data,alipayClient);
		    String result = null;
		    if(isSuccess.get("success")==Boolean.TRUE) {
		    	result=(String) isSuccess.get("result");
		    }
			//输出
		    System.out.println(result);
		    if(!"".equals(result)) {
		    	AlipayTransferResponse alipayTransferResponse=JSONObject.parseObject(result, AlipayTransferResponse.class);
		    	model.addAttribute("transferResponse", alipayTransferResponse);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "transfer";
	}
	
	/**
	 * 为方便商户快速查账，支持商户通过本接口获取商户离线账单下载地址
	 * @param bill_type 
	 * 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
	 * trade指商户基于支付宝交易收单的业务账单；
	 * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单
	 * @param bill_date 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM
	 * @param model
	 * @return
	 */
	@PostMapping("/billDownload.do")
	public String billDownload(
			String bill_type,
			String bill_date, 
			Model model) {
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfig.getGatewayUrl(), 
					alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), 
					alipayConfig.getData_type(), 
					alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(),
					alipayConfig.getSign_type());
		
			BillDownloadBizContent data=
					new BillDownloadBizContent()
		            .setBill_date(bill_date)
		            .setBill_type(bill_type);
			String result =billDownloadurlQuery(JSONObject.toJSONString(data),alipayClient);
			BillDownloadResponse billDownloadResponse=JSONObject.parseObject(result, BillDownloadResponse.class);
	    	model.addAttribute("billDownloadResponse", billDownloadResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bill_download";
	}
	
	/**
	    * 单笔转账到支付宝账户
	    * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.54Ty29&treeId=193&articleId=106236&docType=1
	    * @param content
	    * @return
	    * @throws AlipayApiException
	    */
	   public  Map<String,Object> transfer(AlipayFundTransToaccountTransferModel model,AlipayClient alipayClient) throws AlipayApiException{
	       AlipayFundTransToaccountTransferResponse response = transferToResponse(model,alipayClient);
	       String result = response.getBody();
	       System.out.println("transfer result>"+result);
	       Map<String,Object> map =new HashMap<>();
	       map.put("success", false);
	       if (response.isSuccess()) {
	    	   map.put("success", true);
	    	   map.put("result", result);
	    	   return map;
	       } else {
	           //调用查询接口查询数据
	           JSONObject jsonObject = JSONObject.parseObject(result);
	           String out_biz_no = jsonObject.getJSONObject("alipay_fund_trans_toaccount_transfer_response").getString("out_biz_no");
	           AlipayFundTransOrderQueryModel queryModel = new AlipayFundTransOrderQueryModel();
	           model.setOutBizNo(out_biz_no);
	           String thisResult = transferQuery(queryModel,alipayClient);
	           if (!"".equals(thisResult)) {
	        	   map.put("success", true);
	        	   map.put("result", thisResult);
	        	   return map;
	           }
	       }
	       return map;
	   }
	   
	   public  AlipayFundTransToaccountTransferResponse transferToResponse(AlipayFundTransToaccountTransferModel model,AlipayClient alipayClient) throws AlipayApiException{
	       AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
	       request.setBizModel(model);
	       return alipayClient.execute(request);
	   }
	   
	   /**
	    * 转账查询接口
	    * @param content
	    * @return
	    * @throws AlipayApiException
	    */
	   public  String transferQuery(AlipayFundTransOrderQueryModel model,AlipayClient alipayClient) throws AlipayApiException{
	       AlipayFundTransOrderQueryResponse response = transferQueryToResponse(model,alipayClient);
	       System.out.println("transferQuery result>"+response.getBody());
	       if(response.isSuccess()){
	           return response.getBody();
	       }
	       return null;
	   }
	   
	   public  AlipayFundTransOrderQueryResponse transferQueryToResponse(AlipayFundTransOrderQueryModel model,AlipayClient alipayClient) throws AlipayApiException{
	       AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
	       request.setBizModel(model);
	       return alipayClient.execute(request);
	   }
	   
	   /**
	    * 查询对账单下载地址
	    * @param bizContent
	    * @return
	    * @throws AlipayApiException
	    */
	   public static String billDownloadurlQuery(String bizContent,AlipayClient alipayClient) throws AlipayApiException{
	       AlipayDataDataserviceBillDownloadurlQueryResponse response =  billDownloadurlQueryToResponse(bizContent,alipayClient);
	       System.out.println("AlipayDataDataserviceBillDownloadurlQueryResponse result>"+response.getBody());
	       return response.getBody();
	   }
	   
	   public static AlipayDataDataserviceBillDownloadurlQueryResponse  billDownloadurlQueryToResponse (String bizContent,AlipayClient alipayClient) throws AlipayApiException{
	       AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
	       request.setBizContent(bizContent);
	       return alipayClient.execute(request);
	   }
}
