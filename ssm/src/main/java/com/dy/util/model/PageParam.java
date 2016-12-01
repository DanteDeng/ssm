package com.dy.util.model;

public class PageParam extends BaseModel {

	/**
	* ���к�
	*/
	private static final long serialVersionUID = -8455557852860580586L;
	
	private int pageNum;
	private int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
