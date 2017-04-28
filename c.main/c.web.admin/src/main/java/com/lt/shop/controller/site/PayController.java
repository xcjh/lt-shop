package com.lt.shop.controller.site;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.common.Constant;
import com.common.controller.BaseController;
import com.common.utils.CurrencyUtil;
import com.common.utils.DateUtils;
import com.common.utils.Dom4JUtils;
import com.common.utils.FileUtils;
import com.common.utils.HttpConnection;
import com.common.utils.HttpUtils;
import com.common.utils.MD5;
import com.common.utils.StringUtils;
import com.lt.shop.dao.admin.entity.def.Order;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.service.site.SiteOrderService;

/**
 * 支付
 * 
 * @author arvin
 * @date 2016年8月23日上午9:52:47
 */
@RequestMapping("/pay")
@Controller
public class PayController extends BaseController {
	@Resource
	private SiteOrderService siteOrderService;

	/**
	 * 去支付
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping(value = "/wx-{oid}", method = RequestMethod.GET)
	public ModelAndView weixinPay(@PathVariable Long oid) {
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		User user = (User) contextService.getObject(Constant.SITE_LOGIN_USER);
		if (user == null) {
			return new ModelAndView(new RedirectView("/login"));
		}
		Order order = siteOrderService.getOrder(oid);
		request.setAttribute("order", order);
		// String params = "oid=" + dto.getOrder().getId();
		// String stringSignTemp = params + "&key=" +
		// Constant.WEIXIN_API_SECURITY_KEY;
		// String sign = MD5.md5(stringSignTemp).toUpperCase();
		// request.setAttribute("sign", sign);// 微信签名
		// request.setAttribute("nonceStr", contextService.getSessionId());//
		// 随机串
		// request.setAttribute("appId", Constant.WEIXIN_APP_ID);
		// String prepay_id =
		// EncryptionUtils.authcode(dto.getOrder().getId().toString(), "ENCODE",
		// Constant.WEIXIN_API_SECURITY_KEY, 0);
		// request.setAttribute("prepay_id", prepay_id);
		String[] arr = getXmlStr(order, user);
		String xmlstr = arr[0];
		logger.debug("post------统一下单 params=" + xmlstr);
		String result = HttpConnection.sendPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlstr);
		logger.debug("post------统一下单 result=" + result);
		Map map = Dom4JUtils.parseXml(result);
		if (map != null && map.containsKey("result_code")) {
			if ("SUCCESS".equals(map.get("result_code").toString())) {
				map.put("timeStamp", DateUtils.getDateline());
				map.put("nonceStr", contextService.getSessionId());
				String params = "appId=" + map.get("appid").toString();
				params += "&nonceStr=" + map.get("nonceStr").toString();
				params += "&package=prepay_id=" + map.get("prepay_id").toString();
				params += "&signType=MD5";
				params += "&timeStamp=" + map.get("timeStamp").toString();
				String stringSignTemp = params + "&key=" + Constant.WEIXIN_API_SECURITY_KEY;
				logger.debug("h5调起支付参数：" + stringSignTemp);
				String sign = MD5.md5(stringSignTemp).toUpperCase();
				map.put("mysign", sign);
				logger.debug("h5调起支付map：" + map.toString());
				request.setAttribute("map", map);
				return new ModelAndView("paywx");
			}
		}
		request.setAttribute("msg", "统一下单失败，请返回刷新页面重试！");
		return new ModelAndView("error");
	}

	/**
	 * 获取签名串和xml参数串
	 * 
	 * @param dto
	 * @param user
	 * @return
	 */
	private String[] getXmlStr(Order order, User user) {
		String params = "";
		StringBuffer requestStr = new StringBuffer("<xml>");
		requestStr.append("<appid><![CDATA[");
		requestStr.append(Constant.WEIXIN_APP_ID);
		requestStr.append("]]></appid>");
		params += "appid=" + Constant.WEIXIN_APP_ID;

		String attach = StringUtils.urlEncoderUtf8("悉地之路");
		requestStr.append("<attach><![CDATA[");
		requestStr.append(attach);
		requestStr.append("]]></attach>");
		params += "&attach=" + attach;

		requestStr.append("<body><![CDATA[");
		requestStr.append("JSAPI-" + order.getOrderSn());
		requestStr.append("]]></body>");
		params += "&body=JSAPI-" + order.getOrderSn();

		requestStr.append("<mch_id><![CDATA[");
		requestStr.append(Constant.WEIXIN_MCHID);
		requestStr.append("]]></mch_id>");
		params += "&mch_id=" + Constant.WEIXIN_MCHID;

		requestStr.append("<nonce_str><![CDATA[");
		requestStr.append(contextService.getSessionId());
		requestStr.append("]]></nonce_str>");
		params += "&nonce_str=" + contextService.getSessionId();

		requestStr.append("<notify_url><![CDATA[");
		requestStr.append(Constant.WEIXIN_NOTIFY_URL);
		requestStr.append("]]></notify_url>");
		params += "&notify_url=" + Constant.WEIXIN_NOTIFY_URL;

		requestStr.append("<openid><![CDATA[");
		requestStr.append(user.getOpenid());
		requestStr.append("]]></openid>");
		params += "&openid=" + user.getOpenid();

		String out_trade_no = order.getId() + "_" + order.getOrderSn() + "_" + System.currentTimeMillis();
		requestStr.append("<out_trade_no><![CDATA[");
		requestStr.append(out_trade_no);
		requestStr.append("]]></out_trade_no>");
		params += "&out_trade_no=" + out_trade_no;

		String ip = HttpUtils.getIpAddr(request);
		requestStr.append("<spbill_create_ip><![CDATA[");
		requestStr.append(ip);
		requestStr.append("]]></spbill_create_ip>");
		params += "&spbill_create_ip=" + ip;

		Double amount = CurrencyUtil.mul(order.getAmount().doubleValue(), 100);
		requestStr.append("<total_fee><![CDATA[");
		requestStr.append(amount.intValue() + "");
		requestStr.append("]]></total_fee>");
		params += "&total_fee=" + amount.intValue();

		requestStr.append("<trade_type><![CDATA[");
		requestStr.append("JSAPI");
		requestStr.append("]]></trade_type>");
		params += "&trade_type=JSAPI";

		String stringSignTemp = params + "&key=" + Constant.WEIXIN_API_SECURITY_KEY;
		String sign = MD5.md5(stringSignTemp).toUpperCase();
		// System.out.println("params-->"+params);
		// System.out.println("sign-->"+sign);
		requestStr.append("<sign><![CDATA[");
		requestStr.append(sign);
		requestStr.append("]]></sign>");
		requestStr.append("</xml>");
		String[] arr = { requestStr.toString(), sign };
		return arr;
	}

	/**
	 * 微信支付回调
	 */
	@ResponseBody
	@RequestMapping(value = "/wxnotify", method = RequestMethod.POST, produces = "text/xml;charset=UTF-8")
	public String wxNotify() {

		// 从请求中读取整个post数据
		InputStream inputStream;
		String postData;
		Map map = null;
		try {
			inputStream = request.getInputStream();
			postData = FileUtils.readStreamToString(inputStream);
			if (!StringUtils.isEmpty(postData)) {
				map = Dom4JUtils.parseXml(postData);
			}
			if (map == null || map.get("result_code") == null || map.get("appid") == null
					|| map.get("out_trade_no") == null || map.get("result_code") == null) {
				logger.debug("wxnotify--->返回参数不正确");
				String result = "<xml>";
				result += "<return_code><![CDATA[fail]]></return_code>";
				result += "<return_msg><![CDATA[no]]></return_msg>";
				result += "</xml>";
				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result_code = map.get("result_code").toString();
		if ("SUCCESS".equals(result_code)) {
			String appid = map.get("appid").toString();
			String mch_id = map.get("mch_id").toString();
			String out_trade_no = map.get("out_trade_no").toString();
			logger.debug("wxnotify--->" + "appid----->" + appid);
			logger.debug("wxnotify--->" + "mch_id----->" + mch_id);
			logger.debug("wxnotify--->" + "out_trade_no----->" + out_trade_no);
			if (Constant.WEIXIN_APP_ID.equals(appid) && Constant.WEIXIN_MCHID.equals(mch_id)) {
				if (out_trade_no != null && out_trade_no.indexOf("_") > -1) {
					String[] arr = out_trade_no.split("_");
					Order order = siteOrderService.getOrder(StringUtils.toLong(arr[0]));
					if (order != null && order.getOrderSn().equals(arr[1])) {
						siteOrderService.updateOrderStatus(2, order.getId()); // 0已取消,1新订单,2已支付,3已发货,4已收货,5已完成
						siteOrderService.updatePayStatus(2, order.getId()); // 1未付款，2已付款，3已退款
						String result = "<xml>";
						result += "<return_code><![CDATA[SUCCESS]]></return_code>";
						result += "<return_msg><![CDATA[OK]]></return_msg>";
						result += "</xml>";
						return result;
					}
				}
			}

		}
		logger.debug("wxnotify--->" + "result_code----->" + result_code);
		String result = "<xml>";
		result += "<return_code><![CDATA[fail]]></return_code>";
		result += "<return_msg><![CDATA[no]]></return_msg>";
		result += "</xml>";
		return result;
	}
}
