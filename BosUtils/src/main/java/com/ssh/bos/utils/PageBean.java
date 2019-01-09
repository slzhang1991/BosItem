package com.ssh.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 分页属性封装
 * 
 * @author slzhang
 *
 */
public class PageBean {
	// 总记录数（名字必须是total）
	private Integer total;
	// 当前页码
	private Integer currentPage;
	// 每页显示条数
	private Integer pageSize;
	// 当前页数据集合（名字必须是rows）
	private List rows;
	// 查询条件
	private DetachedCriteria dc;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

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

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public DetachedCriteria getDc() {
		return dc;
	}

	public void setDc(DetachedCriteria dc) {
		this.dc = dc;
	}

}
