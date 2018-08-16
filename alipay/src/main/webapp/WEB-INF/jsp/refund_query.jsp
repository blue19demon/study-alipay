<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
<title>交易退款申请查询</title>
</head>
<body>
交易退款申请查询结果：
<h5>code:${tradeRefundQueryResponse.alipay_trade_fastpay_refund_query_response.code}</h5>
<h5>msg:${tradeRefundQueryResponse.alipay_trade_fastpay_refund_query_response.msg}</h5>
<h5>out_request_no:${tradeRefundQueryResponse.alipay_trade_fastpay_refund_query_response.out_request_no}</h5>
<h5>out_trade_no:${tradeRefundQueryResponse.alipay_trade_fastpay_refund_query_response.out_trade_no}</h5>
<h5>refund_amount:${tradeRefundQueryResponse.alipay_trade_fastpay_refund_query_response.refund_amount}</h5>
<h5>total_amount:${tradeRefundQueryResponse.alipay_trade_fastpay_refund_query_response.total_amount}</h5>
<h5>trade_no:${tradeRefundQueryResponse.alipay_trade_fastpay_refund_query_response.trade_no}</h5>
</body>
</html>