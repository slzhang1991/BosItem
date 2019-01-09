package com.ssh.bos.dao;

import java.util.List;

import com.ssh.bos.dao.base.BaseDao;
import com.ssh.bos.entity.Region;

public interface RegionDao extends BaseDao<Region> {
	/**
	 * 根据条件查询区域列表
	 * 
	 * @param q 查询条件
	 * @return 区域列表
	 */
	List<Region> findListByQ(String q);

}
