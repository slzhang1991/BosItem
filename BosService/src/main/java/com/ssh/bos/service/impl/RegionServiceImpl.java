package com.ssh.bos.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.bos.dao.RegionDao;
import com.ssh.bos.entity.Region;
import com.ssh.bos.service.RegionService;
import com.ssh.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {
    @Autowired
	private RegionDao regionDao;
	
	@Override
	public void saveBatch(Set<Region> regions) {
		// 只需开启一次事务
		for (Region region : regions) {
			// 遍历保存或更新，不会保存重复数据
			regionDao.saveOrUpdate(region);
		}
	}

	@Override
	public void queryPage(PageBean pageBean) {
		regionDao.queryPage(pageBean);
	}

	@Override
	public List<Region> findListByQ(String q) {
		
		return regionDao.findListByQ(q);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

}
