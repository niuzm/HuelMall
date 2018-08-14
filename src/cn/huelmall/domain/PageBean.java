package cn.huelmall.domain;

import java.util.List;

/**
 * 分页bean
 * @author: Administrator
 * @function:TODO
 * @time:  2018年6月27日
 */
public class PageBean<T> {
	private Integer currentPage;//当前页
	private Integer pageSize;//每一页显示的数据量
	private Integer totalNumber;//总条数
	private Integer totalPage; //总页数
	private List<T> dateList; //当前页的数据
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getDateList() {
		return dateList;
	}
	public void setDateList(List<T> dateList) {
		this.dateList = dateList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
