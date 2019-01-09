package com.ssh.bos.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.bos.action.base.BaseAction;
import com.ssh.bos.entity.Staff;
import com.ssh.bos.service.StaffService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 取派员Action
 * 
 * @author slzhang
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	// 属性驱动接收选中的取派员的id
	private String ids;

	@Autowired
	private StaffService staffService;

	/**
	 * 添加取派员
	 * 
	 * @return
	 */
	public String add() {
		staffService.save(model);
		return "list";
	}
	
	/**
	 * 修改取派员
	 * 
	 * @return
	 */
	public String edit() {
		// 根据id查询出原始数据
		Staff staff = staffService.findById(model.getId());
		// 使用页面提交的数据进行覆盖
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staff.setStandard(model.getStandard());
		staff.setHasPda(model.getHasPda());
		// 更新数据
		staffService.updateStaff(staff);
		return "list";
	}

	/**
	 * 查询方法
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryPage() throws IOException {
		// 获取离线查询对象
		DetachedCriteria dc = pageBean.getDc();
		pageBean.setDc(dc);
		// 查询数据
		staffService.queryPage(pageBean);
		// 指定不需要转换的属性
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "currentPage", "pageSize", "dc", "decidedZones" });
		// 将数据转换为json对象
		String json = JSONObject.fromObject(pageBean, jsonConfig).toString();
		// 将数据输出到页面
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return "none";
	}

	/**
	 * 批量删除取派员信息
	 * 
	 * @return 列表页面标识
	 */
	public String deleteBatch() {
		staffService.deleteBatch(ids);
		return "list";
	}
	
	/**
	 * 批量修改取派员删除状态
	 * 
	 * @return 列表页面标识
	 */
	public String restoreBatch() {
		staffService.restoreBatch(ids);
		return "list";
	}
	
	/**
	 * 获取状态正常（delTag = 0）的取派员列表
	 * 
	 * @return none
	 */
	public String getExistStaffListByAjax() {
		List<Staff> staffs = staffService.findExistStaffList();
		// 将集合转换为json字符串
		object2Json(staffs, new String[] {"telephone", "hasPda", "delTag", "station", "standard", "decidedZones"});
		return "none";
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
