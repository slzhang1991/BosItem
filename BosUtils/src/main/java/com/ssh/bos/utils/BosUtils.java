package com.ssh.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ssh.bos.entity.User;

/**
 * BOS工具类
 * 
 * @author slzhang
 *
 */
public class BosUtils {
	/**
	 * 获取HttpSession
	 * 
	 * @return HttpSession对象
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 获取session中登录的用户
	 * 
	 * @return 登录的用户
	 */
	public static User getUser() {
		return (User) getSession().getAttribute("user");
	}
	
}
