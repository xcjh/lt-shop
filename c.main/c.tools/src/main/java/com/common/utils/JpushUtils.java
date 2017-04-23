package com.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Message.Builder;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 
 * @ClassName: JpushUtils
 * @Description: 极光推送Api工具类
 * @author xiaoli
 * @date 2016年12月15日 下午6:11:29
 *
 */
public class JpushUtils {
	protected static final Logger logger = LoggerFactory.getLogger(JpushUtils.class);
	private static String appKey = "48e6b3b41fd25710b97d4051";
	private static String masterSecret = "3cdea449738bd071bb41ee47";
	private static JPushClient jpushClient = new JPushClient(masterSecret, appKey);
	public static boolean IOS_APNS_PRODUCTION = true;// ios极光推送环境，false准生产，true生产

	/**
	 * 
	 * @Title: pushAll @Description: 快捷地构建推送对象：所有平台，所有设备，内容为 msg 的通知 @return
	 * void @throws
	 */
	public static void pushAll(String msg) {
		PushPayload payload = PushPayload.alertAll(msg);
		sendPush(payload);
	}

	/**
	 * 
	 * @Title: pushByUid @Description: 根据用户名给别名推送 @return void @throws
	 */
	public static int pushByUid(String alias, String msg, Map<String, String> extras) {
		return JpushUtils.pushAlias(alias, msg, "路宝拖车", extras);
	}

	/**
	 * 
	 * @Title: pushAlias @Description: 所有平台，推送目标是别名为 "alias"，通知内容为 msg @return
	 * void
	 * .setNotification(Notification.android(msg,"路宝拖车",extras),Notification.ios(msg,
	 * extras)) @throws
	 */
	public static int pushAlias(String alias, String alert, String title, Map<String, String> extras) {
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.alias(alias))
				// .setNotification(Notification.android(alert, title, extras))
				.setNotification(Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder()
						.setAlert(alert).setTitle(title).setBuilderId(1).addExtras(extras).build()).build())
				.build();
		int result = sendPush(payload);
		System.out.println(result);
		payload = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.alias(alias))
				.setNotification(Notification.ios(alert, extras))
				.setOptions(Options.newBuilder().setApnsProduction(IOS_APNS_PRODUCTION).build()).build();
		result = +sendPush(payload);
		System.out.println(result);
		return result;
	}

	/**
	 * 
	 * @Title: sendPush @Description: 消息推送 @return int 1成功 @throws
	 */
	private static int sendPush(PushPayload payload) {
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("jpush result - " + result);
			logger.info("jpush result - " + result.msg_id);
			logger.info("jpush result - " + result.sendno);
			return 1;

		} catch (APIConnectionException e) {
			// Connection error, should retry later
			logger.error("jpush Connection error, should retry later", e);

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			logger.error("jpush Should review the error, and fix the request", e);
			logger.info("jpush HTTP Status: " + e.getStatus());
			logger.info("jpush Error Code: " + e.getErrorCode());
			logger.info("jpush Error Message: " + e.getErrorMessage());
		}
		return 0;
	}

	public static void main(String[] args) {
		String msg = "您有订单" + System.currentTimeMillis() + "需要处理";
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("tc_orderId", "77");
		extras.put("tc_detailInfo", msg);
		extras.put("tc_pushType", "1");
//		JpushUtils.pushByUid("TCZSC_17_ALIAS", msg, extras);
		JpushUtils.pushAlias("TCZSC_78_ALIAS", msg,  "路宝拖车", extras);
		JpushUtils.pushAlias("TCZSC_84_ALIAS", msg,  "路宝拖车", extras);
	}

}
