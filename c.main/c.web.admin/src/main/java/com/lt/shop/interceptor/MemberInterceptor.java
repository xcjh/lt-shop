package com.lt.shop.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.Constant;
import com.common.service.ContextService;
import com.lt.shop.dao.admin.entity.def.User;

/**
 * 
* @ClassName: MemberInterceptor 
* @Description: 会员中心登录拦截器
* @author xiaoli
* @date 2016年12月5日 下午8:47:03 
*
 */
public class MemberInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private ContextService contextService;
	@Override	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// 在DispatcherServlet完全处理完请求后被调用  
		
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView modelAndView) throws Exception {
		// 在业务处理器处理请求之后被调用
		
	}
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// 在业务处理器处理请求之前被调用
		String uri = request.getServletPath();
		User user = (User)this.contextService.getObject(Constant.SITE_LOGIN_USER);
		if(user==null){
			//没有登录或者登录失效
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		
		return true;
	}

}
