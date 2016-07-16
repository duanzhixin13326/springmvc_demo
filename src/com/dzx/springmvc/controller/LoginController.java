package com.dzx.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dzx.springmvc.entity.User;

@Controller
public class LoginController {
	/***
	 * 首页 返回至/page/home.jsp页面
	 * 
	 * @return
	 * 
	 * 		URL->http://localhost:8080/springmvc_demo/preLogin.html
	 */
	@RequestMapping("preLogin")
	public ModelAndView preLogin() {
		// 创建模型跟视图，用于渲染页面。并且指定要返回的页面为home页面
		return new ModelAndView("preLogin");
	}

	/***
	 * 用户登陆
	 * <p>
	 * 注解配置，只允许POST提交到该方法
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 * 		URL->doLogin.html
	 */
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 验证传递过来的参数是否正确，否则返回到登陆页面。
		if (this.checkParams(new String[] { username, password })) {
			// 指定要返回的页面为success.jsp
			ModelAndView modelAndView = new ModelAndView("success");
			// 将参数返回给页面
			// modelAndView.addObject("username", username);
			// modelAndView.addObject("password", password);
			
//			modelAndView.addObject("data", new User(username, password));

			modelAndView.addObject("data", JSON.parseObject("{'username':'" + username + "','password':'" + password + "'}", User.class));
			return modelAndView;
		}
		return new ModelAndView("preLogin");
	}

	/***
	 * 验证参数是否为空
	 * 
	 * @param params
	 * @return
	 */
	private boolean checkParams(String[] params) {
		for (String param : params) {
			if (param == "" || param == null || param.isEmpty()) {
				return false;
			}
		}
		return true;
	}
}