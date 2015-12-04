package com.rying.service;

import com.rying.easyui.model.Datagrid;
import com.rying.entity.SysUser;
import com.rying.exception.PasswordNotMatchException;
import com.rying.exception.SysUserNotFoundException;
import com.rying.exception.UsernameNotFoundException;
import com.rying.model.SysUserQueryVo;

public interface ISysUserService {

	SysUser login(String username, String password) throws UsernameNotFoundException, PasswordNotMatchException;

	SysUser getUserById(int id);

	Datagrid<SysUser> datagrid(SysUserQueryVo queryVo, String order, String sort, int page, int rows);

	void deleteById(int id) throws SysUserNotFoundException;

	void addRole(int _id, int[] _roleIds);

	void addSysUser(SysUser sysUser);

	void delete(SysUser sysUser);

	void update(SysUser sysUser);

	boolean exist(SysUser admin);

}
