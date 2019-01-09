package com.ssh.bos.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ssh.bos.entity.User;
import com.ssh.bos.utils.BosUtils;

/**
 * 自定义登录拦截器
 * 
 * @author slzhang
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 从session中获取用户对象
		User user = BosUtils.getUser();
		// 判断用户对象是否存在
		if (user == null) {
			return "login";
		} else {
			// 放行
			return invocation.invoke();
		}
	}

}
