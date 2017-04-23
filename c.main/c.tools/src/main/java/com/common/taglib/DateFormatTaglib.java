package com.common.taglib;

import java.util.Date;

import javax.servlet.jsp.JspException;

import com.common.utils.DateUtils;


public class DateFormatTaglib extends TagSupport {

	
	private Long time;
	private String times;
	private String pattern;//时间格式
	
	public int doEndTag() throws JspException {
		time = time==null?0l:time;
		time = times== null?time:Long.valueOf(times);
		pattern = pattern==null?"yyyy-MM-dd HH:mm:ss":pattern;
		if(time>0){
			if(time.toString().length()==10){
				time = time*1000;
			}
			Date date = new Date(time);
			String str  = DateUtils.toString(date, pattern);
			this.print(str);
		}else{
			this.print("");
		}
		return this.EVAL_BODY_INCLUDE;
	}

	
	public int doStartTag() throws JspException {
		return this.EVAL_PAGE;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
}
