package com.lt.shop.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.common.utils.ExceptionUtils;

/**
 * 
 * @ClassName: CommonExceptionHandler
 * @Description: 异常处理
 * @author xiaoli
 * @date 2016年12月26日 下午1:44:00
 *
 */
public class CommonExceptionHandler implements HandlerExceptionResolver {
	protected static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String uri = request.getServletPath();
		if (uri != null && uri.startsWith("/admin/")) {
			ModelAndView mv = new ModelAndView();
			/* 使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常 */
			FastJsonJsonView view = new FastJsonJsonView();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("result", "2");
			attributes.put("message", "运行时错误");
			attributes.put("data", new HashMap<String,Object>());
			view.setAttributesMap(attributes);
			mv.setView(view);
			logger.info("运行时错误->" + uri + "->" + ExceptionUtils.getTrace(ex));
			return mv;
		}else{
			uri = uri==null?"":uri;
			logger.info("其他运行时错误->" + uri + "->" + ExceptionUtils.getTrace(ex));
		}
		return null;
	}

}
