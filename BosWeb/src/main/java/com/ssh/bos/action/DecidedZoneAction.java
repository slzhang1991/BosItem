package com.ssh.bos.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.bos.action.base.BaseAction;
import com.ssh.bos.entity.DecidedZone;
import com.ssh.bos.service.DecidedZoneService;

/**
 * 定区Action
 * 
 * @author slzhang
 */
@Controller
@Scope("prototype")
public class DecidedZoneAction extends BaseAction<DecidedZone> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DecidedZoneService decidedZoneService;
	// 属性驱动接收多个分区id
	private String[] subAreaId;

	/**
	 * 保存定区
	 * 
	 * @return list
	 */
	public String addDecidedZone() {
		decidedZoneService.saveDecidedZone(model, subAreaId);
		return "list";
	}
	
	/**
	 * 定区分页查询
	 * 
	 * @return
	 */
	public String queryPage() {
		decidedZoneService.queryPage(pageBean);
		object2Json(pageBean, new String[] {"subAreas", "decidedZones"});
		return "none";
	}

	public void setSubAreaId(String[] subAreaId) {
		this.subAreaId = subAreaId;
	}

}
