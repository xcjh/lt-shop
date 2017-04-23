package com.common.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.utils.HttpUtils;
import com.common.utils.StringUtils;


/**
 * 上下文业务处理类
 * @author xiaokx
 * @date 2013-9-2下午3:44:27
 */
@Service
public class ContextService<T> {
	
	/**
	 * 保存实体到session中
	 * @author xiaokx
	 * @date 2013-9-2下午3:50:00
	 * @param key
	 * @param obj
	 */
	public void setObject(String key,T t){
		 ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().setAttribute(key, t);
	}
	
	/**
	 * session中获取实体
	 * @author xiaokx
	 * @date 2013-9-2下午3:55:04
	 * @param key
	 * @return
	 */
	public T getObject(String key){
		return (T)((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(key);
	}
	
	/**
	 * 删除session保存的值
	 * @author xiaokx
	 * @date 2013-9-11下午3:36:50
	 * @param key
	 */
	public void deleteKey(String key){
		((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().removeAttribute(key);
	}
	
	
	/**
	 * 获取当前浏览器SessionId
	 * @author xiaokx
	 * @date 2013-9-2下午3:56:27
	 * @return
	 */
	public String getSessionId(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String sid= HttpUtils.getCookieValue(request, "uidstr");
		if(StringUtils.isEmpty(sid)){
			sid=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getSessionId();
		}
		return sid;
	}
	
//	@Autowired
//	private EmailProducer mailMessageProducer;
//	
//	public void sendRegEmail(Member member){
//		try {
//			String checkurl  = "http://m.meijing.com/memberemailcheck.html?s="+ EncryptionUtils.authcode(member.getMember_id()+","+member.getRegtime(), "ENCODE","",0);
//			EmailModel emailModel = new EmailModel();
//			emailModel.getData().put("username", member.getUname());
//			emailModel.getData().put("checkurl", checkurl);
//			emailModel.setTitle(member.getUname()+"您好， 美睛网会员注册成功!");
//			emailModel.setTo(member.getEmail());
//			emailModel.setTemplate("reg_email_template.html");
//			mailMessageProducer.send(emailModel);	
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(DateUtils.toString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")+" 发送注册邮件失败");
//		}	
//	}
//	
//	/**
//	 * 发送邮件
//	 * @param emailModel
//	 */
//	public void sendEmail(EmailModel emailModel) {
//		try {
//			String email = emailModel.getTo();
//			if(StringUtils.isEmpty(email)) return;
//			email = email.toLowerCase();
//			if(StringUtils.isEmpty(email) || email.indexOf("@temp")>-1 || email.indexOf("@51fanli")>-1) return;
//			mailMessageProducer.send(emailModel);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(DateUtils.toString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")+" 发送邮件失败");
//		}
//		
//	}

}
