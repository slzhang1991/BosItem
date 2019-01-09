package com.ssh.bos.service;

import com.ssh.bos.entity.DecidedZone;
import com.ssh.bos.utils.PageBean;

public interface DecidedZoneService {
	/**
	 * 保存定区
	 * 
	 * @param model
	 * @param subAreaIds
	 */
	void saveDecidedZone(DecidedZone model, String[] subAreaIds);
	
	/**
	 * 分页查询定区
	 * 
	 * @param pageBean
	 */
	void queryPage(PageBean pageBean);

}
