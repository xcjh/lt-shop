package com.lt.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.common.utils.RespResult;

import net.sf.json.JSONObject;


/**
 * 
* @ClassName: FileUploadInterceptor 
* @Description: 文件上传拦截器，控制文件大小用
* @author xiaoli
* @date 2016年12月20日 下午3:45:57 
*
 */
public class FileUploadInterceptor implements HandlerInterceptor {
	private long maxSize;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request != null && ServletFileUpload.isMultipartContent(request)) {
			ServletRequestContext ctx = new ServletRequestContext(request);
			long requestSize = ctx.contentLength();
			if (requestSize > maxSize) {
				RespResult resp = new RespResult();
				resp.setResult(2);
				resp.setMessage("总文件大小不能超过10M");
				JSONObject jsonObject = JSONObject.fromObject(resp);
				response.getWriter().write(jsonObject.toString());
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}
}
