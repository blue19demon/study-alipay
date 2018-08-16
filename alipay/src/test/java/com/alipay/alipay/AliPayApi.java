package com.alipay.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;


/**
 * 提现、对账单下载
 * @author Administrator
 *
 */
public class AliPayApi {
	
    private static	AlipayClient alipayClient = null;
    
	public static void main(String[] args) {
		try {
			/*String total_amount = "100";
			    AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
			    model.setOutBizNo(new Date().getTime()+"");//生成订单号
			    model.setPayeeType("ALIPAY_LOGONID");//固定值
			    model.setPayeeAccount("pwcyeq1339@sandbox.com");//转账收款账户
			    model.setAmount(total_amount);
			    model.setPayerShowName("测试退款");
			    model.setPayerRealName("沙箱环境");//账户真实名称
			    model.setRemark("java测试单笔转账到支付宝");
			    Boolean isSuccess=false;
			    try {
					isSuccess = AliPayApi.transfer(model);
				   } catch (Exception e) {
					e.printStackTrace();
			    }
			    System.out.println(isSuccess);*/
			
			System.out.println(billDownloadurlQuery("{" +
					"\"bill_type\":\"trade\"," +
					"\"bill_date\":\"2018-08-13\"" +
					"  }"));;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static {
		//获得初始化的AlipayClient
		alipayClient = new DefaultAlipayClient(
				AlipayDEVConfig.gatewayUrl,
				AlipayDEVConfig.app_id,
				AlipayDEVConfig.merchant_private_key,
				AlipayDEVConfig.data_type,
				AlipayDEVConfig.charset,
				AlipayDEVConfig.alipay_public_key,
				AlipayDEVConfig.sign_type);
	}
	
	/**
    * 单笔转账到支付宝账户
    * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.54Ty29&treeId=193&articleId=106236&docType=1
    * @param content
    * @return
    * @throws AlipayApiException
    */
   public static boolean transfer(AlipayFundTransToaccountTransferModel model) throws AlipayApiException{
       AlipayFundTransToaccountTransferResponse response = transferToResponse(model);
       String result = response.getBody();
       System.out.println("transfer result>"+result);
       if (response.isSuccess()) {
           return true;
       } else {
           //调用查询接口查询数据
           JSONObject jsonObject = JSONObject.parseObject(result);
           String out_biz_no = jsonObject.getJSONObject("alipay_fund_trans_toaccount_transfer_response").getString("out_biz_no");
           AlipayFundTransOrderQueryModel queryModel = new AlipayFundTransOrderQueryModel();
           model.setOutBizNo(out_biz_no);
           boolean isSuccess = transferQuery(queryModel);
           if (isSuccess) {
               return true;
           }
       }
       return false;
   }
   
   public static AlipayFundTransToaccountTransferResponse transferToResponse(AlipayFundTransToaccountTransferModel model) throws AlipayApiException{
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
   public static boolean transferQuery(AlipayFundTransOrderQueryModel model) throws AlipayApiException{
       AlipayFundTransOrderQueryResponse response = transferQueryToResponse(model);
       System.out.println("transferQuery result>"+response.getBody());
       if(response.isSuccess()){
           return true;
       }
       return false;
   }
   
   public static AlipayFundTransOrderQueryResponse transferQueryToResponse(AlipayFundTransOrderQueryModel model) throws AlipayApiException{
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
   public static String billDownloadurlQuery(String bizContent) throws AlipayApiException{
       AlipayDataDataserviceBillDownloadurlQueryResponse response =  billDownloadurlQueryToResponse(bizContent);
       System.out.println("AlipayDataDataserviceBillDownloadurlQueryResponse result>"+response.getBody());
       return response.getBillDownloadUrl();
   }
   
   public static AlipayDataDataserviceBillDownloadurlQueryResponse  billDownloadurlQueryToResponse (String bizContent) throws AlipayApiException{
       AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
       request.setBizContent(bizContent);
       return alipayClient.execute(request);
   }
}
