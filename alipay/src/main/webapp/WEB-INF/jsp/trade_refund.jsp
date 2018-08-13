<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>交易退款申请</title>
</head>
<body>
交易退款申请结果：
<h3>code:${tradeRefundResponse.alipay_trade_refund_response.code}</h3>
<h3>msg:${tradeRefundResponse.alipay_trade_refund_response.msg}</h3>
<h3>sub_code:${tradeRefundResponse.sub_code}</h3>
<h3>sub_msg:${tradeRefundResponse.sub_msg}</h3>
<h3>trade_no:${tradeRefundResponse.alipay_trade_refund_response.trade_no}</h3>
<h3>out_trade_no:${tradeRefundResponse.alipay_trade_refund_response.out_trade_no}</h3>
<h3>buyer_logon_id:${tradeRefundResponse.alipay_trade_refund_response.buyer_logon_id}</h3>
<h3>fund_change:${tradeRefundResponse.alipay_trade_refund_response.fund_change}</h3>
<h3>buyer_user_id:${tradeRefundResponse.alipay_trade_refund_response.buyer_user_id}</h3>
<h3>refund_fee:${tradeRefundResponse.alipay_trade_refund_response.refund_fee}</h3>
<h3>send_back_fee:${tradeRefundResponse.alipay_trade_refund_response.send_back_fee}</h3>
<h3>gmt_refund_pay:${tradeRefundResponse.alipay_trade_refund_response.gmt_refund_pay}</h3>
</body>
</html>