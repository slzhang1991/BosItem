package com.ssh.bos.service;

import java.util.List;

import com.ssh.bos.entity.Staff;
import com.ssh.bos.utils.PageBean;

/**
 * 取派员业务层
 * 
 * @author slzhang
 *
 */
public interface StaffService {
	/**
	 * 保存取派员
	 * 
	 * @param staff
	 */
	void save(Staff staff);
	
	/**
	 * 分页查询取派员
	 * 
	 * @param pageBean
	 */
	void queryPage(PageBean pageBean);
	
	/**
	 * 根据id批量删除取派员
	 * 
	 * @param ids
	 */
	void deleteBatch(String ids);
	
	/**
	 * 根据取派员id查询
	 * 
	 * @param id 取派员id
	 * @return 取派员对象
	 */
	Staff findById(String id);
	
	/**
	 * 更新取派员
	 * 
	 * @param staff
	 */
	void updateStaff(Staff staff);
	
	/**
	 * 批量修改取派员删除状态
	 * 
	 * @param ids
	 */
	void restoreBatch(String ids);

	/**
	 * 获取状态正常的员工列表
	 * 
	 * @return list<Staff>
	 */
	List<Staff> findExistStaffList();
}
