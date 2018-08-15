<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
<title>订单查询</title>
</head>
<body>
订单查询结果：
<h3>code:${bizContentTradeQueryResponse.code}</h3>
<h3>msg:${bizContentTradeQueryResponse.msg}</h3>
<h3>sub_code:${bizContentTradeQueryResponse.sub_code}</h3>
<h3>sub_msg:${bizContentTradeQueryResponse.sub_msg}</h3>
<h3>trade_no:${bizContentTradeQueryResponse.alipay_trade_query_response.trade_no}</h3>
<h3>out_trade_no:${bizContentTradeQueryResponse.alipay_trade_query_response.out_trade_no}</h3>
<h3>buyer_logon_id:${bizContentTradeQueryResponse.alipay_trade_query_response.buyer_logon_id}</h3>
<h3>buyer_pay_amount:${bizContentTradeQueryResponse.alipay_trade_query_response.buyer_pay_amount}</h3>
<h3>buyer_user_id:${bizContentTradeQueryResponse.alipay_trade_query_response.buyer_user_id}</h3>
<h3>buyer_user_type:${bizContentTradeQueryResponse.alipay_trade_query_response.buyer_user_type}</h3>
<h3>invoice_amount:${bizContentTradeQueryResponse.alipay_trade_query_response.invoice_amount}</h3>
<h3>point_amount:${bizContentTradeQueryResponse.alipay_trade_query_response.point_amount}</h3>
<h3>receipt_amount:${bizContentTradeQueryResponse.alipay_trade_query_response.receipt_amount}</h3>
<h3>send_pay_date:${bizContentTradeQueryResponse.alipay_trade_query_response.send_pay_date}</h3>
<h3>total_amount:${bizContentTradeQueryResponse.alipay_trade_query_response.total_amount}</h3>
<h3>trade_status:${bizContentTradeQueryResponse.alipay_trade_query_response.trade_status}</h3>
</body>
</html>