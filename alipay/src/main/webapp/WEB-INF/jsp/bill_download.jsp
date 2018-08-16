<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" type="image/x-icon" href="/static/favicon.ico">
<title>对账单下载</title>
</head>
<body>
<c:if test="${billDownloadResponse.alipay_data_dataservice_bill_downloadurl_query_response.code  eq '10000'}">
   <h5>对账单生成成功：</h5><a href="${billDownloadResponse.alipay_data_dataservice_bill_downloadurl_query_response.bill_download_url }" >点击下载</a>(30秒后未下载，链接地址失效。)
</c:if>
<c:if test="${billDownloadResponse.alipay_data_dataservice_bill_downloadurl_query_response.code ne '10000'}">
   <h5>对账单生成失败：</h5>
   ${billDownloadResponse.alipay_data_dataservice_bill_downloadurl_query_response.msg},
   ${billDownloadResponse.alipay_data_dataservice_bill_downloadurl_query_response.sub_code},
   ${billDownloadResponse.alipay_data_dataservice_bill_downloadurl_query_response.sub_msg}
</c:if>
</body>
</html>