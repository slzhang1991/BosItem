package com.ssh.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.bos.dao.DecidedZoneDao;
import com.ssh.bos.dao.SubAreaDao;
import com.ssh.bos.entity.DecidedZone;
import com.ssh.bos.entity.SubArea;
import com.ssh.bos.service.DecidedZoneService;
import com.ssh.bos.utils.PageBean;

/**
 * 
 * @author slzhang
 */
@Service
@Transactional
public class DecidedZoneServiceImpl implements DecidedZoneService {
	@Autowired
	private DecidedZoneDao decidedZoneDao;
	@Autowired
	private SubAreaDao subAreaDao;

	@Override
	public void saveDecidedZone(DecidedZone model, String[] subAreaIds) {
		// 保存定区
		decidedZoneDao.save(model);
		// 遍历分区id，查询出分区，让分区关联定区
		for (String subAreaId : subAreaIds) {
			// 多的一方维护外键
			SubArea subArea = subAreaDao.findById(subAreaId);
			// 分区关联定区
			subArea.setDecidedZone(model);
		}
	}

	@Override
	public void queryPage(PageBean pageBean) {
		decidedZoneDao.queryPage(pageBean);
	}

}
