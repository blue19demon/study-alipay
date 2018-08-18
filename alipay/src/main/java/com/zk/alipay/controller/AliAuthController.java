package com.zk.alipay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.zk.alipay.config.AlipayConfig;

/**
 * 阿里授权登录
 * 
 * @author Administrator
 *
 */
@Controller
public class AliAuthController {

	@Autowired
	private AlipayConfig alipayConfig;

	/**
	 * 用户登陆授权
	 * 
	 * @return
	 */
	@GetMapping("/auth_login.do")
	public String authLogin(Model model) {
		return "auth_login";
	}

	/**
	 * 授权
	 * 
	 * @return
	 */
	@RequestMapping("/auth.do")
	public String auth(Model model) {
		String auth_login = alipayConfig.getAuthReqUrl();
		auth_login = auth_login.replaceAll("APPID", alipayConfig.getApp_id()).replaceAll("ENCODED_URL",
				alipayConfig.getRedirect_uri());
		return "redirect:" + auth_login;
	}

	/**
	 * 接受auth_code
	 * 
	 * @param app_id
	 * @param source
	 * @param app_auth_code
	 * @param model
	 * @return
	 */
	@RequestMapping("/authUrl.htm")
	public String getToken(String app_id, String scope, String auth_code, Model model) {
		try {
			// 获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getApp_id(),
					alipayConfig.getMerchant_private_key(), alipayConfig.getData_type(), alipayConfig.getCharset(),
					alipayConfig.getAlipay_public_key(), alipayConfig.getSign_type());
			// 获取用户信息授权
			AlipaySystemOauthTokenRequest requestLogin2 = new AlipaySystemOauthTokenRequest();
			requestLogin2.setCode(auth_code);
			requestLogin2.setGrantType("authorization_code");
			AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(requestLogin2);
			System.out.println("<br/>AccessToken:" + oauthTokenResponse.getAccessToken() + "\n");
			AlipayUserInfoShareRequest requestUser = new AlipayUserInfoShareRequest();
			try {
				AlipayUserInfoShareResponse userinfoShareResponse = alipayClient.execute(requestUser,
						oauthTokenResponse.getAccessToken());
				System.out.println("<br/>UserId:" + userinfoShareResponse.getUserId() + "\n");// 用户支付宝ID
				System.out.println("UserType:" + userinfoShareResponse.getUserType() + "\n");// 用户类型
				System.out.println("UserStatus:" + userinfoShareResponse.getUserStatus() + "\n");// 用户账户动态
				System.out.println("Email:" + userinfoShareResponse.getEmail() + "\n");// 用户Email地址
				System.out.println("IsCertified:" + userinfoShareResponse.getIsCertified() + "\n");// 用户是否进行身份认证
				System.out.println("IsStudentCertified:" + userinfoShareResponse.getIsStudentCertified() + "\n");// 用户是否进行学生认证
			} catch (AlipayApiException e) {
				// 处理异常
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "auth_result";
	}
}
