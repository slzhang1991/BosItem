package com.ssh.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssh.bos.dao.UserDao;
import com.ssh.bos.entity.User;
import com.ssh.bos.service.UserService;
import com.ssh.bos.utils.BosUtils;
import com.ssh.bos.utils.Md5Utils;

/**
 * 用户接口
 * 
 * @author slzhang
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		// 使用md5加密登录密码
		String securePassword = Md5Utils.md5(user.getPassword());
		return userDao.findUserByNameAndPwd(user.getUsername(), securePassword);
	}

	@Override
	public void editPassword(String id, String password) {
		// 对密码进行md5加密
		String newPassword = Md5Utils.md5(password);
		// 更新密码
		userDao.updateObject("user.editPassword", newPassword, id);
	}
	
}
