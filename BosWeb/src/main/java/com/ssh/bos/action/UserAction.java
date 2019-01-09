package com.ssh.bos.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.bos.action.base.BaseAction;
import com.ssh.bos.entity.User;
import com.ssh.bos.service.UserService;
import com.ssh.bos.utils.BosUtils;

/**
 * User控制器
 * 
 * @author slzhang
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	@Autowired
	private UserService userService;

	// 属性驱动接收验证码
	private String checkCode;

	/**
	 * 用户登录
	 * 
	 * @return 首页标识
	 */
	public String login() {
		// 从session中获取页面生成的验证码
		String generatorCheckCode = (String) BosUtils.getSession().getAttribute("key");
		// 校验输入的验证码是否正确
		if (StringUtils.isNotBlank(checkCode) && checkCode.toLowerCase().equals(generatorCheckCode.toLowerCase())) {
			// 输入验证码正确，执行登录操作
			User existUser = userService.login(model);
			// 判断用户是否为空
			if (existUser != null) {
				// 登录成功，将User对象放入session，跳转到首页
				BosUtils.getSession().setAttribute("user", existUser);
				return "home";
			} else {
				// 提示错误信息，跳转到登录页面
				addActionError("用户名或密码错误！");
				return "login";
			}
		} else {
			// 输入验证码错误，提示错误信息
			addActionError("验证码错误！");
			return "login";
		}
	}

	/**
	 * 用户退出
	 * 
	 * @return 退出到用户登录页面
	 */
	public String logout() {
		// 销毁session
		BosUtils.getSession().invalidate();
		// 跳转到登录页面
		return "login";
	}

	/**
	 * 修改密码
	 * 
	 * @throws IOException
	 * @return null
	 */
	public String editPassword() throws IOException {
		// 从session中获取user对象（获取对象id）
		User user = BosUtils.getUser();
		String flag = "1";
		try {
			// 调用userService修改密码方法
			userService.editPassword(user.getId(), model.getPassword());
		} catch (Exception e) {
			flag = "0";
			e.printStackTrace();
		}
		// 设置向页面输出的内容为普通文本（默认为对象）
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		// 向页面输出标识符
		ServletActionContext.getResponse().getWriter().println(flag);
		// 不做处理
		return null;
	}

	/**
	 * setter和getter方法
	 */
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

}
