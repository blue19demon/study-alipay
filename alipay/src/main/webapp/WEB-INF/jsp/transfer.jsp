<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
<title>提现</title>
</head>
<body>
提现结果：
<h5>code:${transferResponse.alipay_fund_trans_toaccount_transfer_response.code}</h5>
<h5>msg:${transferResponse.alipay_fund_trans_toaccount_transfer_response.msg}</h5>
<h5>order_id:${transferResponse.alipay_fund_trans_toaccount_transfer_response.order_id}</h5>
<h5>out_biz_no:${transferResponse.alipay_fund_trans_toaccount_transfer_response.out_biz_no}</h5>
<h5>pay_date:${transferResponse.alipay_fund_trans_toaccount_transfer_response.pay_date}</h5>
</body>
</html>