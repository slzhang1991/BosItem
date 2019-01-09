package com.ssh.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssh.bos.dao.RegionDao;
import com.ssh.bos.dao.base.impl.BaseDaoImpl;
import com.ssh.bos.entity.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

	@Override
	public List<Region> findListByQ(String q) {
		String hql = "FROM Region r WHERE r.shortCode LIKE ? OR r.cityCode LIKE ? OR r.province LIKE ? OR r.city LIKE ? OR r.district LIKE ?";
		List<Region> regionList = (List<Region>) getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%");
		return regionList;
	}
	
}
