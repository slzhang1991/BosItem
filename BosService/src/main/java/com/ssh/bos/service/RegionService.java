package com.ssh.bos.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.ssh.bos.entity.Region;
import com.ssh.bos.utils.PageBean;

public interface RegionService {

	/**
	 * 批量保存区域
	 * 
	 * @param regions
	 */
	void saveBatch(Set<Region> regions);
	
	/**
	 * 分页查询区域列表
	 * 
	 * @param pageBean
	 */
	void queryPage(PageBean pageBean);
	
	/**
	 * 根据条件（q）查询区域列表
	 * 
	 * @param q
	 * @return
	 */
	List<Region> findListByQ(String q);
	
	/**
	 * 查询区域列表
	 * 
	 * @return
	 */
	List<Region> findAll();

}
