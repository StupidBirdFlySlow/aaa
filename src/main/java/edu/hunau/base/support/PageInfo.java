package edu.hunau.base.support;

import java.util.List;

public class PageInfo<E> {

	private int prepage;
	private int nextpage;
	private int pageNum;// 页码
	private int pageSize;
	private int totalPage;// 共有多少页
	private int totalRecords;// 有多少条记录
	private int showPageNum;//显示多少页
	private List<E> list;// page中存储的显示数据

	public PageInfo() {
		getPrepage();
		getTotalPage();
		getShowPageNum();
	}

	public PageInfo(int pageNum,int pageSize,int totalRecords,List<E> list){
		this.pageNum=pageNum;
		this.pageSize=pageSize;
		this.totalRecords=totalRecords;
		this.list=list;
		getPrepage();
		getTotalPage();
		getNextpage();
		getShowPageNum();
	}
	
	public int getPrepage() {
		if(pageNum<=1){
			prepage=1;
		}else{
			this.prepage=pageNum-1;
		}
		return prepage;
	}

	public int getNextpage() {
		if(nextpage>=totalPage){
			nextpage=totalPage;
		}else{
			nextpage=pageNum+1;
		}
		return nextpage;
	}

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

	public int getShowPageNum() {
		int temp= totalPage-pageNum;
		if(temp/5>=1){
			showPageNum=5;
		}else{
			showPageNum=temp+1;
		}
		return showPageNum;
	}

	/**
	 * 获得总页数
	 * 
	 * @return
	 */
	public int getTotalPage() {
		if ((this.pageSize == 0) || (this.totalRecords == 0)) {
			return 1;
		}
		if (this.totalRecords % this.pageSize == 0) {
			totalPage = this.totalRecords / this.pageSize;
			return totalPage;
		}
		totalPage = this.totalRecords / this.pageSize + 1;
		return totalPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

}
