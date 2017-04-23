package com.common.utils;

import java.io.Serializable;
import java.util.List;

public class Pager implements Serializable{
	
	/**
	 * 页面显示数量
	 */
	private int pageSize;
	
	/**
	 * 当前显示页码
	 */
	private int pageNo;
	
	/**
	 * 总记录数
	 */
	private int total;
	/**
	 * 当前页结果集
	 */
	private List datas;
	
	/**
	 * 页面总数
	 */
	private int pageCount;
	
	/**
	 * 页面显示串
	 */
	private String showhtml;
	
	public Pager(){}
	
	public Pager(List datas,int total,int pageNo,int pageSize){
		this.datas = datas;
		this.total = total;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		
		if (total % pageSize == 0)
			this.pageCount =  total / pageSize;
		else
			this.pageCount =  total / pageSize + 1;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getShowhtml() {
		return showhtml;
	}

	public void setShowhtml(String showhtml) {
		this.showhtml = showhtml;
	}

}
