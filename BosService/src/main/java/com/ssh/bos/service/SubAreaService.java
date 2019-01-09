package com.ssh.bos.service;

import java.util.List;

import com.ssh.bos.entity.SubArea;
import com.ssh.bos.utils.PageBean;

public interface SubAreaService {
	/**
	 * 分页查询区域数据
	 * 
	 * @param pageBean
	 */
	void queryPage(PageBean pageBean);

	/**
	 * 保存分区
	 * 
	 * @param model
	 */
	void saveSubArea(SubArea model);

	/**
	 * 查询分区列表
	 * 
	 * @return List<SubArea>
	 */
	List<SubArea> findAll();
	
	/**
	 * 查询未分配的分区列表
	 * 
	 * @return List<SubArea>
	 */
	List<SubArea> findUnallocatedSubAreaList();

}
