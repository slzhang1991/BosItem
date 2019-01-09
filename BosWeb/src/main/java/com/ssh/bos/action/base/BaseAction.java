package com.ssh.bos.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.bos.utils.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Action公共实现
 * 
 * @author slzhang
 *
 * @param <T> 实体类
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	// 创建分页对象
	protected PageBean pageBean = new PageBean();
	// 离线查询对象
	DetachedCriteria dc = null;
	// 实体类的类型
	private Class<T> clazz;
	// 实体类的引用（子类可以调用）
	protected T model;

	/**
	 * 接收参数（page）并设置当前页
	 * 
	 * @param page
	 */
	public void setPage(Integer page) {
		pageBean.setCurrentPage(page);
	}

	/**
	 * 接收参数（rows）并设置每页条数
	 * 
	 * @param rows
	 */
	public void setRows(Integer rows) {
		pageBean.setPageSize(rows);
	}

	/**
	 * 默认构造方法
	 */
	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 参数数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		clazz = (Class<T>) actualTypeArguments[0];
		// 设置DetachedCriteria对象
		pageBean.setDc(DetachedCriteria.forClass(clazz));
		try {
			// 通过反射创建对象
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return model;
	}

	/**
	 * 将java对象转换为json对象
	 * 
	 * @param obj
	 * @param excludes
	 */
	protected void object2Json(Object obj, String[] excludes) {
		// 创建json配置对象
		JsonConfig jsonConfig = new JsonConfig();
		// 指定不需要转换json的属性
		jsonConfig.setExcludes(excludes);
		// 将java对象转换为json字符串
		String json = JSONObject.fromObject(obj, jsonConfig).toString();
		// 设置转换格式
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将List集合转换为json对象
	 * 
	 * @param list
	 * @param excludes
	 */
	protected void object2Json(List list, String[] excludes) {
		// 创建json配置对象
		JsonConfig jsonConfig = new JsonConfig();
		// 指定不需要转换json的属性
		jsonConfig.setExcludes(excludes);
		// 将java对象转换为json字符串
		String json = JSONArray.fromObject(list, jsonConfig).toString();
		// 设置转换格式
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
