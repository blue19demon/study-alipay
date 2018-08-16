<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
<title>交易退款申请</title>
</head>
<body>
交易退款申请结果：
<h5>code:${tradeRefundResponse.alipay_trade_refund_response.code}</h5>
<h5>msg:${tradeRefundResponse.alipay_trade_refund_response.msg}</h5>
<h5>sub_code:${tradeRefundResponse.sub_code}</h5>
<h5>sub_msg:${tradeRefundResponse.sub_msg}</h5>
<h5>trade_no:${tradeRefundResponse.alipay_trade_refund_response.trade_no}</h5>
<h5>out_trade_no:${tradeRefundResponse.alipay_trade_refund_response.out_trade_no}</h5>
<h5>buyer_logon_id:${tradeRefundResponse.alipay_trade_refund_response.buyer_logon_id}</h5>
<h5>fund_change:${tradeRefundResponse.alipay_trade_refund_response.fund_change}</h5>
<h5>buyer_user_id:${tradeRefundResponse.alipay_trade_refund_response.buyer_user_id}</h5>
<h5>refund_fee:${tradeRefundResponse.alipay_trade_refund_response.refund_fee}</h5>
<h5>send_back_fee:${tradeRefundResponse.alipay_trade_refund_response.send_back_fee}</h5>
<h5>gmt_refund_pay:${tradeRefundResponse.alipay_trade_refund_response.gmt_refund_pay}</h5>
</body>
</html>