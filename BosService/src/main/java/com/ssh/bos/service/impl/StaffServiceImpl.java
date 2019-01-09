package com.ssh.bos.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.bos.dao.StaffDao;
import com.ssh.bos.entity.Staff;
import com.ssh.bos.service.StaffService;
import com.ssh.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffDao staffDao;
	
	@Override
	public void save(Staff staff) {
		staffDao.save(staff);
	}

	@Override
	public void queryPage(PageBean pageBean) {
		staffDao.queryPage(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] staffIds = ids.split(",");
			for (String staffId : staffIds) {
				staffDao.updateObject("staff.delete", staffId);
			}
		}
	}

	@Override
	public Staff findById(String id) {
		return staffDao.findById(id);
	}

	@Override
	public void updateStaff(Staff staff) {
		staffDao.update(staff);
	}

	@Override
	public void restoreBatch(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] staffIds = ids.split(",");
			for (String staffId : staffIds) {
				staffDao.updateObject("staff.restore", staffId);
			}
		}
	}

	@Override
	public List<Staff> findExistStaffList() {
		DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
		// 封装查询条件（delTag = 0）
		dc.add(Restrictions.eq("delTag", "0"));
		return staffDao.findObjByCriteria(dc);
	}

}
