package com.rying.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rying.dao.IRoleDao;
import com.rying.dao.ISysUserDao;
import com.rying.easyui.model.Datagrid;
import com.rying.entity.Role;
import com.rying.entity.SysUser;
import com.rying.exception.PasswordNotMatchException;
import com.rying.exception.SysUserNotFoundException;
import com.rying.exception.UsernameNotFoundException;
import com.rying.model.SysUserQueryVo;
import com.rying.util.DateUtil;
import com.rying.util.StrValidator;

@Service
public class SysUserService implements ISysUserService {
	private Logger logger = Logger.getLogger(SysUserService.class);
	private SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	@Resource
	private ISysUserDao sysUserDao;
	@Resource
	private IRoleDao roleDao;
	@Resource
	private IInvestorRelationsService investorRelationsService;

	@Override
	public SysUser login(String username, String password)
			throws UsernameNotFoundException, PasswordNotMatchException {
		SysUser sysUser = this.getSysUserByUsername(username);

		if (sysUser == null) {
			throw new UsernameNotFoundException();
		}

		if (!password.equals(sysUser.getPassword())) {
			throw new PasswordNotMatchException();
		}

		return sysUser;
	}

	private SysUser getSysUserByUsername(String username) {
		String hql = "from SysUser as sysUser where sysUser.username = ?";
		List<SysUser> userList = this.sysUserDao.queryListByHQL(hql,
				new String[] { username });

		if (userList != null && !userList.isEmpty()) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public SysUser getUserById(int id) {
		return this.sysUserDao.getById(id);
	}

	@Override
	public void addSysUser(SysUser sysUser) {
		if (sysUser != null) {
			Date currentDate = DateUtil.getCurrentDate();
			sysUser.setCreatetime(DateUtil.getCurrentDate());
			sysUser.setModifytime(currentDate);

			sysUserDao.save(sysUser);
		}
	}

	@Override
	public void delete(SysUser sysUser) {
		this.sysUserDao.delete(sysUser);
	}

	@Override
	public void deleteById(int id) throws SysUserNotFoundException {
		SysUser sysUser = sysUserDao.getById(id);
		if (sysUser == null)
			throw new SysUserNotFoundException();
		sysUser.setRoles(null);

		deleteOthers(sysUser);
		
		this.delete(sysUser);
	}

	private void deleteOthers(SysUser sysUser) {
		// 删除投资者关系
		this.investorRelationsService.deleteIrBySysUser(sysUser);
	}

	@Override
	public void update(SysUser sysUser) {
		SysUser entity = this.getUserById(sysUser.getId());
		if (entity != null) {
			entity.setEmail(sysUser.getEmail());
			entity.setModifytime(new Date());
			entity.setName(sysUser.getName());
			entity.setPassword(sysUser.getPassword());
			entity.setPhone(sysUser.getPhone());
			entity.setUsername(sysUser.getUsername());
			sysUserDao.update(entity);
		}
	}

	@Override
	public Datagrid<SysUser> datagrid(SysUserQueryVo queryVo, String order,
			String sort, int page, int rows) {
		StringBuffer countHql = new StringBuffer(
				"select count(*) from SysUser as sysUser");
		StringBuffer pageHql = new StringBuffer("from SysUser as sysUser");

		StringBuffer where = new StringBuffer(" where 1 = 1");

		List<Object> list = new ArrayList<Object>();
		if (StrValidator.isNotNullAndEmpty(queryVo.getName())) {
			where.append(" and sysUser.name = ?");
			list.add(queryVo.getName());
		}

		if (StrValidator.isNotNullAndEmpty(queryVo.getEmail())) {
			where.append(" and sysUser.email = ?");
			list.add(queryVo.getEmail());
		}
		try {
			if (StrValidator.isNotNullAndEmpty(queryVo.getEndCreateTime())
					&& StrValidator.isNotNullAndEmpty(queryVo
							.getStartCreateTime())) {
				where.append(" and (sysUser.createtime >= ?").append(
						" and sysUser.createtime <= ?");
				list.add(DEFAULT_DATE_FORMAT.parse(queryVo.getStartCreateTime()));
				list.add(DEFAULT_DATE_FORMAT.parse(queryVo.getEndCreateTime()));
			}
			if (StrValidator.isNotNullAndEmpty(queryVo.getEndModifyTime())
					&& StrValidator.isNotNullAndEmpty(queryVo
							.getStartModifyTime())) {
				where.append(" and (sysUser.modifytime >= ?").append(
						" and sysUser.modifytime <= ?)");
				list.add(DEFAULT_DATE_FORMAT.parse(queryVo.getStartModifyTime()));
				list.add(DEFAULT_DATE_FORMAT.parse(queryVo.getEndModifyTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (StrValidator.isNotNullAndEmpty(queryVo.getPhone())) {
			where.append(" and sysUser.phone = ?");
			list.add(queryVo.getPhone());
		}
		if (StrValidator.isNotNullAndEmpty(queryVo.getUsername())) {
			where.append(" and sysUser.username = ?");
			list.add(queryVo.getUsername());
		}

		countHql.append(where);
		pageHql.append(where).append(" order by sysUser.id desc");

		logger.info(countHql);
		logger.info(pageHql);

		Object[] values = list.toArray();
		Long count = this.sysUserDao.countByHql(countHql.toString(), values);
		if (count == 0) {
			return new Datagrid<SysUser>(0, Collections.<SysUser> emptyList());
		}
		List<SysUser> pageList = this.sysUserDao.queryListByHQL(
				pageHql.toString(), values, page, rows);

		return new Datagrid<SysUser>(count.intValue(), pageList);

	}

	@Override
	public void addRole(int id, int[] roleIds) {
		SysUser sysUser = this.sysUserDao.getById(id);
		sysUser.getRoles().clear();

		for (int roleId : roleIds) {
			Role role = roleDao.getById(roleId);
			sysUser.getRoles().add(role);
		}

		sysUserDao.update(sysUser);
	}

	@Override
	public boolean exist(SysUser admin) {
		int id = admin.getId();
		if(id != 0) {
			SysUser su = this.getUserById(id);
			return su != null;
		}
		String username = admin.getUsername();
		if(StrValidator.isNotNullAndEmpty(username)) {
			SysUser su = this.getSysUserByUsername(username);
			return su != null;
		}
		return false;
	}
}
