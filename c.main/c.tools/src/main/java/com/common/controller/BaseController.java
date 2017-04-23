package com.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.Constant;
import com.common.service.ContextService;
import com.common.utils.GsonUtils;
import com.common.utils.HttpUtils;
import com.common.utils.PageHtmlBuilder;
import com.common.utils.Pager;
import com.common.utils.RespResult;
import com.common.utils.StringUtils;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;

/**
 * 
* @ClassName: BaseController 
* @Description: 控制器基类
* @author xiaoli
* @date 2016年10月8日 下午4:51:08 
*
 */
@Controller
@Scope("prototype")
public abstract class BaseController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	public static final int RESULTCODE_SUCCESS_REDIRECT = 111;//成功，并带转向地址
	public static final int RESULTCODE_SUCCESS = 0;//成功返回码
	public static final int RESULTCODE_SESSIONKEY_INVALID = 1;//sessionkey失效返回码
	public static final int RESULTCODE_ERROR = 2;//通用错误返回码
	
	/**
	 * 上下文
	 */
	@Autowired
	protected ContextService contextService;

	/**
	 * 获取HttpServletRequest
	 * 
	 * @return HttpServletRequest
	 */
	protected HttpServletRequest getRequest() {
		return request;
	}

	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		// 将sessionid写入cookie中
		String sid = HttpUtils.getCookieValue(request, "uidstr");
		if (StringUtils.isEmpty(sid)) {
			sid = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getSessionId();
			HttpUtils.addCookie(response, "uidstr", sid, 182 * 24 * 60 * 60);
		}
		Constant.CONTEXTPATH = request.getContextPath();
		request.setAttribute("ctx", request.getContextPath());
	}

	protected Pager webpage;

	/**
	 * 构建分页列表html元素，需要分页数据构建完成后再执行
	 */
	protected void loadPagerHtml() {
		webpage.setShowhtml(new PageHtmlBuilder(webpage.getPageNo(), webpage
				.getTotal(), webpage.getPageSize(), this.getRequest())
				.buildPageHtml());
	}
	
	
	/**
	 * 
	 * @Title: resp @Description: 生成接口请求返回json串，参数参考RespResult属性 @return
	 * String @throws
	 */
	protected String resp(Object... args) {
		RespResult resp = new RespResult();
		for (Object object : args) {
			if (object instanceof Integer) {
				resp.setResult(StringUtils.toInt(object.toString()));
			} else if (object instanceof String) {
				resp.setMessage(object.toString());
			} else if (object instanceof Object) {
				resp.setData(object);
			}
		}
		return showObject(resp);
	}
	
	/**
	 * 
	* @Title: vailJson 
	* @Description: 验证json数据格式是否匹配
	* @return boolean 
	* @throws
	 */
	protected boolean vailJsonByList(String json,String... keys){
		List<Map<String,Object>> listImg = GsonUtils.toObject(json,  new TypeToken<List<Map<String,Object>>>(){});
		if(listImg==null){
			return false;
		}
		for (Map<String, Object> map : listImg) {
			if(map.size()!=keys.length)return false; 
			for (String key : keys) {
				if(!map.containsKey(key))return false;
			}
		}
		return true;
	}
	
	/**
	 * 返回json数据
	* @Title: showObject 
	* @Description: 这里用一句话描述这个方法的作用 
	* @return String 
	* @throws
	 */
	protected String showObject(Object obj){
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject.toString();
	}
	
	/**
	 * 
	* @Title: getMsg 
	* @Description: 获取validator中的提示内容
	* @return String 
	* @throws
	 */
	protected String getMsg(BindingResult result){
		String msg = "";
		List<ObjectError> errors = result.getAllErrors();
		for (ObjectError objectError : errors) {
			msg+=","+objectError.getDefaultMessage();
		}
		return msg.replaceFirst(",", "");
	}
	

	protected int pageSize = 15;
}
