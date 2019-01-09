package com.ssh.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ssh.bos.dao.base.BaseDao;
import com.ssh.bos.utils.PageBean;

/**
 * 数据访问公共接口实现
 * 
 * @author slzhang
 *
 * @param <T> 实体类
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	// 实体类的类型
	private Class<T> clazz;

	/**
	 * 默认构造方法
	 */
	public BaseDaoImpl() {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		clazz = (Class<T>) actualTypeArguments[0];
	}

	/**
	 * 注解开发，无法重写父类final修饰的setSessionFactory(SessionFactory sessionFactory)方法
	 * 
	 * @param sessionFactory SessionFactory实例
	 */
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		// 先根据id获取，再删除对象
		delete(findById(id));
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		String hql = "FROM " + clazz.getSimpleName();
		return (List<T>) getHibernateTemplate().find(hql);
	}

	@Override
	public void updateObject(String queryName, Object... objects) {
		// 获取当前session
		Session session = getSessionFactory().getCurrentSession();
		// 创建查询对象
		Query query = session.getNamedQuery(queryName);
		// 设置查询参数
		int i = 0;
		for (Object object : objects) {
			query.setParameter(i++, object);
		}
		// 执行更新
		query.executeUpdate();
	}

	@Override
	public void queryPage(PageBean pageBean) {
		// 查询条件
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDc();

		// 查询总记录
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> countList = (List<Long>) getHibernateTemplate().findByCriteria(detachedCriteria);
		pageBean.setTotal(countList.get(0).intValue());
		// 清空查询条件
		detachedCriteria.setProjection(null);

		// 指定hibernate框架封装对象的方式
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);

		// 查询当前页数据集合
		Integer firstResult = (currentPage - 1) * pageSize;
		Integer maxResults = pageSize;
		List rows = getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}

	@Override
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public List<T> findObjByCriteria(DetachedCriteria dc) {
		return (List<T>) getHibernateTemplate().findByCriteria(dc);
	}

}
