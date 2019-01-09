package com.ssh.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ssh.bos.utils.PageBean;

/**
 * 数据访问公共接口
 * 
 * @author slzhang
 *
 * @param <T> 实体类
 */
public interface BaseDao<T> {
	/**
	 * 添加对象
	 * 
	 * @param entity 对象
	 */
	void save(T entity);
	
	/**
	 *保存或更新
	 * 
	 * @param entity
	 */
	void saveOrUpdate(T entity);

	/**
	 * 删除对象
	 * 
	 * @param entity 对象
	 */
	void delete(T entity);
	
	/**
	 * 根据id删除对象
	 * 
	 * @param id 对象id
	 */
	void deleteById(Serializable id);

	/**
	 * 更新对象
	 * 
	 * @param entity 对象
	 */
	void update(T entity);

	/**
	 * 根据id查询对象
	 * 
	 * @param id 对象id
	 * @return 查询到的对象
	 */
	T findById(Serializable id);

	/**
	 * 查询对象列表
	 * 
	 * @return 对象列表
	 */
	List<T> findAll();
	
	/**
	 * 更新对象
	 * 
	 * @param queryName hql语句
	 * @param objects 需要更新的属性
	 */
	void updateObject(String queryName, Object...objects);
	
	/**
	 * 分页查询
	 * 
	 * @param pageBean
	 */
	void queryPage(PageBean pageBean);
	
	/**
	 * 根据封装条件查询
	 * 
	 * @param dc
	 * @return list
	 */
	List<T> findObjByCriteria(DetachedCriteria dc);
	

}