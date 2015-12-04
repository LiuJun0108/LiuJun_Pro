package com.rying.service;

import com.rying.entity.SysUser;

public interface IAdminService {
	boolean isAdmin(String username);
	
	SysUser getAdminFromConfig();
}
