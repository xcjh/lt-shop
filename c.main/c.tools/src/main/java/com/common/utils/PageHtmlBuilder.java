package com.common.utils;

import javax.servlet.http.HttpServletRequest;

public class PageHtmlBuilder {
	
	protected String url;
	protected HttpServletRequest request ;
	private long pageNum;
	private long totalCount;
	private int pageSize;
	private long pageCount;
	private int showCount = 5;
	
	public PageHtmlBuilder(long _pageNum,long _totalCount,int _pageSize,HttpServletRequest _request){
		pageNum= _pageNum;
		totalCount= _totalCount;
		pageSize= _pageSize ;
		request = _request;
	}
	
	public String buildPageHtml() {
		this.init();
		StringBuffer pageStr = new StringBuffer("");
		pageStr.append("<div class=\"page\" >" );
		pageStr.append(this.getHeadString());
		pageStr.append(this.getBodyString());
		pageStr.append(this.getFooterString());
		pageStr.append("</div>");
//		String pagecss = "<style type='text/css'>";
//		pagecss += ".page{padding-top:10px;padding-left:10px;height:30px;background:#ffffff;}";
//		pagecss += ".page .info{float:left;padding:2px 3px 0 0 ;}";
//		pagecss += ".selected{float:left;background:none repeat scroll 0 0 #FF9900;border:1px solid #FFFFFF;color:#FFFFFF;margin:0 2px;padding:1px 5px;}";
//		pagecss += ".unselected{float:left;background:none repeat scroll 0 0 #ccc;border:1px solid #FFFFFF;color:#FFFFFF;margin:0 2px;padding:1px 5px;}";
//		pagecss += "</style>";
//		pageStr.append(pagecss);
		return pageStr.toString();
	}

	
	
	/**
	 * 初始化url,用于地址栏方式传
	 * <br/> 
	 * 将地址栏上的参数拼装
	 *
	 */
	protected  void initUrl() {
		String uri = request.getContextPath()+RequestUtils.getRequestUrl(request);
		if(uri.contains("?")){
			String [] arr = uri.split("\\?");
			url = arr[0];
			url = url +"0";
			url = url.replaceAll("\\d+", "#p")+"?"+arr[1];
		}else{
			url = uri;
			url = url +"0";
			url = url.replaceAll("\\d+", "#p");
		}
		
//		url =request.getContextPath()+RequestUtils.getRequestUrl(request);
//		url = url.replaceAll("(&||\\?)p=(\\d+)","");
//		url = url.replaceAll("(&||\\?)rmd=(\\d+)","");
//		url =url.indexOf('?')>0?url+"&": url + "?";
	}
	
	
	/**
	 * 计算并初始化信息
	 *
	 */
	private  void init() {	
 
		pageSize = pageSize<1? 1 :pageSize;
		
		pageCount = totalCount / pageSize;
		pageCount = totalCount % pageSize > 0 ? pageCount + 1 : pageCount;
	
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		pageNum = pageNum < 1 ? 1 : pageNum;
		
		if(this.url==null)
		initUrl();
//		url = url.indexOf('?') >= 0 ? (url += "&") : (url += "?");
	}
	
	
	 /**
	  * 生成分页头字串
	  * @return
	  */
	protected String getHeadString() {

		StringBuffer headString = new StringBuffer("");
		headString.append("<span class=\"info\" >");
		headString.append("共");
		headString.append(this.totalCount);
		headString.append("条记录");
		headString.append("</span>\n");

		headString.append("<span class=\"info\">");
		headString.append(this.pageNum);
		headString.append("/");
		if(this.pageCount==0){
			headString.append(1);
		}
		else{
			headString.append(this.pageCount);
		}
		headString.append("</span>\n");

		headString.append("<ul>");
		if (pageNum > 1) {

			headString.append("<li style=\"float:left\"><a " );
			headString.append(" class=\"unselected\" ");
			headString.append(this.getUrlStr(1));
			headString.append("|&lt;");
			headString.append("</a></li>\n");

			headString.append("<li style=\"float:left\"><a  ");
			headString.append(" class=\"unselected\" ");
			headString.append(this.getUrlStr(pageNum - 1));
			headString.append("&lt;&lt;");
			headString.append("</a></li>\n");
		}

		return headString.toString();
	}

	/**
	 * 生成分页尾字串
	 * @return
	 */
	protected String getFooterString() {
		StringBuffer footerStr = new StringBuffer("");
		if (pageNum < pageCount) {

			footerStr.append("<li style=\"float:left\"><a ");
			footerStr.append(" class=\"unselected\" ");
			footerStr.append(this.getUrlStr(pageNum + 1));
			footerStr.append("&gt;&gt;");
			footerStr.append("</a></li>\n");

			footerStr.append("<li style=\"float:left\"><a ");
			footerStr.append(" class=\"unselected\" ");
			footerStr.append(this.getUrlStr(pageCount));
			footerStr.append("&gt;|");
			footerStr.append("</a></li>\n");

		}
		footerStr.append("</ul>");
		return footerStr.toString();
	}

	/**
	 * 生成分页主体字串
	 * @return
	 */
	protected String getBodyString() {

		StringBuffer pageStr = new StringBuffer();

		long start = pageNum - showCount / 2;
		start = start <= 1 ? 1 : start;

		long end = start + showCount;
		end = end > pageCount ? pageCount : end;

		for (long i = start; i <= end; i++) {

			pageStr.append("<li style=\"float:left\"><a ");
			if (i != pageNum) {
				pageStr.append(" class=\"unselected\"");
				pageStr.append(this.getUrlStr(i));
			} else {
				pageStr.append(" class=\"selected\">");
			}

		 
			pageStr.append(i);
			pageStr.append("</a></li>\n");

		}

		return pageStr.toString();
	}

	/**
	 * 生成href的字串
	 */
	
	protected String getUrlStr(long page) {
		StringBuffer linkHtml = new StringBuffer();
		linkHtml.append("href='");
		linkHtml.append(url.replace("#p", page+""));
//		linkHtml.append("p=");
//		linkHtml.append(page);
		linkHtml.append("'>");
		return linkHtml.toString();
		
		 
	}
}
