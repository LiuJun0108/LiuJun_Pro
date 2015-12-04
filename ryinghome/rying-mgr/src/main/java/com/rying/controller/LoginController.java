package com.rying.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rying.entity.Resources;
import com.rying.entity.SysUser;
import com.rying.exception.PasswordNotMatchException;
import com.rying.exception.UsernameNotFoundException;
import com.rying.service.IRoleService;
import com.rying.service.ISysUserService;

@Controller
@SessionAttributes({ "currentUser", "auths" })
public class LoginController extends BaseController {
	@Resource
	private ISysUserService sysUserService;
	@Resource
	private IRoleService roleService;

	@RequestMapping("/login")
	public String login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, ModelMap model) {

		try {
			SysUser loginUser = this.sysUserService.login(username, password);
			loginUser.setRoles(null);
			loginUser.setPassword("******");
			model.addAttribute("currentUser", loginUser);

			List<String> auths = this.getAuthority(loginUser);
			model.addAttribute("auths", auths);

			return "main";
		} catch (UsernameNotFoundException e) {
			model.addAttribute("resultMsg", "用户名不存在");
		} catch (PasswordNotMatchException e) {
			model.addAttribute("resultMsg", "密码错误");
		}
		return "login";
	}

	private List<String> getAuthority(SysUser u) {
		List<Resources> auths = this.roleService.findResourcesBySysUserId(u.getId());

		List<String> resultList = new ArrayList<>(auths.size());
		for (Resources auth : auths) {
			resultList.add(auth.getAction());
			String menuUrl = auth.getUpperMenu().getUrl();
			if (!resultList.contains(menuUrl))
				resultList.add(menuUrl);
		}
		return resultList;
	}

}
