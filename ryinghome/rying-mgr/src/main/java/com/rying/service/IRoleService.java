package com.rying.service;

import java.util.List;

import com.rying.easyui.model.Datagrid;
import com.rying.entity.Resources;
import com.rying.entity.Role;
import com.rying.exception.ResourcesNotFoundException;
import com.rying.exception.RoleNotFoundException;
import com.rying.exception.SysUserNotFoundException;


public interface IRoleService {

	/**
	 * 分页 排序
	 * 
	 * @param order
	 * @param sort
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Datagrid<Role> datagrid(String order, String sort, int page, int pageSize);

	void deleteByPK(int id) throws RoleNotFoundException;

	List<Role> findRolesBySysUserId(int userId) throws SysUserNotFoundException;

	public List<Role> queryAll();

	/**
	 * 以角色ID拿到其下资源列表
	 * 
	 * @param roleId
	 * @return
	 */
	List<Resources> findResourcesByRoleId(int roleId) throws ResourcesNotFoundException;

	/**
	 * 绑定资源
	 * 
	 * @param roldId
	 * @param _reIds
	 */
	void bindResources(int roleId, int[] _reIds) throws RoleNotFoundException;

	/**
	 * 以userId查权限列表
	 * 
	 * @param userId
	 * @return
	 * @exception SysException
	 */
	public List<Resources> findResourcesBySysUserId(int userId);

	Role getByPk(int id);

	void add(Role entity);

	void delete(Role entity);

	void update(Role entity);
}
