<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
<title>交易关闭</title>
</head>
<body>
交易关闭结果：
<h5>code:${tradeCloseResponse.alipay_trade_close_response.code}</h5>
<h5>msg:${tradeCloseResponse.alipay_trade_close_response.msg}</h5>
<h5>sub_code:${tradeCloseResponse.alipay_trade_close_response.sub_code}</h5>
<h5>sub_msg:${tradeCloseResponse.alipay_trade_close_response.sub_msg}</h5>
<h5>out_trade_no:${tradeCloseResponse.alipay_trade_close_response.out_trade_no}</h5>
</body>
</html>