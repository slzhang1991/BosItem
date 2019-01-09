package com.ssh.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssh.bos.dao.UserDao;
import com.ssh.bos.dao.base.impl.BaseDaoImpl;
import com.ssh.bos.entity.User;

/**
 * User数据访问接口实现
 * 
 * @author slzhang
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findUserByNameAndPwd(String username, String password) {
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = (List<User>) getHibernateTemplate().find(hql, username, password);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
