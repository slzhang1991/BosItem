package com.ssh.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.bos.dao.SubAreaDao;
import com.ssh.bos.entity.SubArea;
import com.ssh.bos.service.SubAreaService;
import com.ssh.bos.utils.PageBean;

/**
 * 分区业务层
 * 
 * @author slzhang
 *
 */
@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService {
	@Autowired
	private SubAreaDao subAreaDao;

	@Override
	public void queryPage(PageBean pageBean) {
		subAreaDao.queryPage(pageBean);
	}

	@Override
	public void saveSubArea(SubArea model) {
		subAreaDao.save(model);
	}

	@Override
	public List<SubArea> findAll() {
		return subAreaDao.findAll();
	}

	@Override
	public List<SubArea> findUnallocatedSubAreaList() {
		DetachedCriteria dc = DetachedCriteria.forClass(SubArea.class);
		// 封装查询条件
		dc.add(Restrictions.isNull("decidedZone"));
		return subAreaDao.findObjByCriteria(dc);
	}

}
