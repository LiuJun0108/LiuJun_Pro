package com.rying.service;

import org.springframework.stereotype.Service;

import com.rying.entity.SysUser;
import com.rying.util.PropertiesUtil;

@Service
public class AdminService implements IAdminService {
	private static final String ADMIN_USERNAME_KEY = "admin_username";
	private static final String ADMIN_PASSWORD_KEY = "admin_password";
	private static SysUser admin;

	@Override
	public boolean isAdmin(String username) {
		SysUser admin = this.getAdminFromConfig();
		return admin.getUsername().equals(username);
	}

	@Override
	public SysUser getAdminFromConfig() {
		if (admin == null) {
			String admin_username = PropertiesUtil.get(ADMIN_USERNAME_KEY);
			String admin_password = PropertiesUtil.get(ADMIN_PASSWORD_KEY);

			admin = new SysUser();
			admin.setUsername(admin_username);
			admin.setPassword(admin_password);
		}
		return admin;
	}

}
