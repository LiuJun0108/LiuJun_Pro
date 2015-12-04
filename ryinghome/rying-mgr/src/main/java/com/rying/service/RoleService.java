package com.rying.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rying.dao.IRoleDao;
import com.rying.dao.ISysUserDao;
import com.rying.easyui.model.Datagrid;
import com.rying.entity.Resources;
import com.rying.entity.Role;
import com.rying.entity.SysUser;
import com.rying.exception.ResourcesNotFoundException;
import com.rying.exception.RoleNotFoundException;
import com.rying.exception.SysUserNotFoundException;
import com.rying.util.DateUtil;
import com.rying.util.StrValidator;

@Service
public class RoleService implements IRoleService {
	// private static Logger logger = Logger.getLogger(RoleServiceImpl.class);
	@Resource
	private IRoleDao roleDao;
	@Resource
	private ISysUserDao sysUserDao;

	@Override
	public Role getByPk(int id) {
		return this.roleDao.getById(id);
	}

	@Override
	public void add(Role entity) {
		Date currentDate = DateUtil.getCurrentDate();
		entity.setCreatetime(currentDate);
		entity.setModifytime(currentDate);
		roleDao.save(entity);
	}

	@Override
	public void delete(Role entity) {
		roleDao.delete(entity);
	}

	@Override
	public void deleteByPK(int id) throws RoleNotFoundException {
		Role entity = getByPk(id);
		if (entity == null)
			throw new RoleNotFoundException();

		delete(entity);
	}

	@Override
	public void update(Role role) {
		Role entity = this.getByPk(role.getId());
		if(entity != null) {
			entity.setModifytime(new Date());
			entity.setName(role.getName());
			
			this.roleDao.update(entity);
		}
	}

	@Override
	public Datagrid<Role> datagrid(String order, String sort, int page, int pageSize) {
		if (page < 1 || pageSize < 1) {
			new Datagrid<Role>(0, new ArrayList<Role>());
		}

		Long total = roleDao.countAll();
		// logger.info("total= " + total);

		if (total > 0) {
			StringBuffer hql = new StringBuffer("from Role as role");
			if(StrValidator.isNotNullAndEmpty(sort) && StrValidator.isNotNullAndEmpty(order)) {
				hql.append(" by role.").append(sort).append(" ").append(order);
			}
			List<Role> list = roleDao.queryListByHQL(hql.toString(), page, pageSize);
			// logger.info("listSize= " + list.size());

			return new Datagrid<Role>(total.intValue(), list);
		} else {
			// logger.info("listSize= 0");
			return new Datagrid<Role>(0, new ArrayList<Role>());
		}
	}

	@Override
	public List<Role> findRolesBySysUserId(int userId) throws SysUserNotFoundException {
		// logger.info("查找用户下的角色。。。");
		SysUser sysUser = sysUserDao.getById(userId);
		if (sysUser == null)
			throw new SysUserNotFoundException();

		Set<Role> set = sysUser.getRoles();
		return new ArrayList<Role>(set);
	}

	public List<Role> queryAll() {
		return roleDao.queryAll();
	}

	@Override
	public List<Resources> findResourcesByRoleId(int roleId) throws ResourcesNotFoundException {
		// logger.info("查找角色下的资源。。。");

		Role role = this.getByPk(roleId);
		if (role == null)
			throw new ResourcesNotFoundException("角色不存在id=" + roleId);

		return new ArrayList<Resources>(role.getResources());
	}

	@Override
	public void bindResources(int roleId, int[] _reIds) throws RoleNotFoundException {
		// logger.info("为角色绑定资源。。。");

		Role role = this.getByPk(roleId);
		if (role == null)
			throw new RoleNotFoundException("角色不存在id=" + roleId);

		role.getResources().clear();

		for (int reId : _reIds) {
			// Resources re = resourcesDao.findById(reId);
			Resources re2 = new Resources();
			re2.setId(reId);
			role.getResources().add(re2);
		}

		update(role);

	}

	@Override
	public List<Resources> findResourcesBySysUserId(int userId) {

		List<Resources> resultList = new ArrayList<Resources>();

		try {
			List<Role> roleList = findRolesBySysUserId(userId);
			for (Role role : roleList) {
				int roleId = role.getId();
				List<Resources> reList = findResourcesByRoleId(roleId);
				for (Resources re : reList) {
					if (!resultList.contains(re)) {
						resultList.add(re);
					}
				}
			}
		} catch (SysUserNotFoundException e) {
			throw new RuntimeException(e);
		} catch (ResourcesNotFoundException e) {
			throw new RuntimeException(e);
		}
		return resultList;
	}

}
