package com.zk.alipay.controller;

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
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.zk.alipay.config.AlipayConfig;
import com.zk.alipay.request.PayCloseBizContent;
import com.zk.alipay.request.TradeQueryBizContent;
import com.zk.alipay.request.TradeRefundBizContent;
import com.zk.alipay.request.TradeRefundQueryBizContent;
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
}
