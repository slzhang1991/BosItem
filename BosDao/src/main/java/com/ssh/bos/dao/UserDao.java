package com.ssh.bos.dao;

import com.ssh.bos.dao.base.BaseDao;
import com.ssh.bos.entity.User;

/**
 * User接口
 * 
 * @author slzhang
 *
 */
public interface UserDao extends BaseDao<User> {
	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @return 查询到的用户
	 */
	User findUserByNameAndPwd(String username, String password);
}
