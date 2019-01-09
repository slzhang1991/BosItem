package com.ssh.bos.service;

import com.ssh.bos.entity.User;

/**
 * 用户接口
 * 
 * @author slzhang
 *
 */
public interface UserService {
	/**
	 * 用户登录
	 * 
	 * @param user 用户对象
	 * @return 登录的用户
	 */
	User login(User user);
	
	/**
	 * 修改密码
	 * 
	 * @param id 用户id
	 * @param password 新密码
	 */
	void editPassword(String id, String password);
}
