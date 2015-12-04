package com.rying.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rying.easyui.model.Datagrid;
import com.rying.easyui.model.JsonMsg;
import com.rying.entity.Resources;
import com.rying.entity.Role;
import com.rying.exception.ResourcesNotFoundException;
import com.rying.exception.RoleNotFoundException;
import com.rying.exception.SysUserNotFoundException;
import com.rying.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private IRoleService roleService;

	@RequestMapping("/datagrid")
	@ResponseBody
	public Datagrid<Role> datagrid(int page, int rows, String order, String sort) {
		Datagrid<Role> datagrid = roleService.datagrid(order, sort, page, rows);
		return datagrid;
	}

	@RequestMapping("/datagridWithNoPagination")
	@ResponseBody
	public List<Role> datagridWithNoPagination() {
		List<Role> allRoles = roleService.queryAll();
		return allRoles;
	}

	@RequestMapping("/addRole")
	@ResponseBody
	public JsonMsg addRole(Role role) {
		roleService.add(role);
		return new JsonMsg(true, "保存成功");
	}

	@RequestMapping("/updateRole")
	@ResponseBody
	public JsonMsg updateRole(Role role) {
		roleService.update(role);

		Map<String, String> re = new HashMap<String, String>(1);
		re.put("flag", "update");
		return new JsonMsg(true, "修改成功", re);
	}

	@RequestMapping("/destroyRole")
	@ResponseBody
	public JsonMsg destroyRole(int id) {
		try {
			roleService.deleteByPK(id);
			return new JsonMsg(true, "删除成功");
		} catch (RoleNotFoundException e) {
			return new JsonMsg(true, "删除失败");
		}

	}

	@RequestMapping("/findRolesBySysUserId")
	@ResponseBody
	public List<Role> findRolesBySysUserId(int sysUserId) {
		try {
			return roleService.findRolesBySysUserId(sysUserId);
		} catch (SysUserNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/findResourcesByRoleId")
	@ResponseBody
	public List<Resources> findResourcesByRoleId(int roleId) {
		try {
			List<Resources> list = roleService.findResourcesByRoleId(roleId);
			return list; 
		} catch (ResourcesNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/addResources")
	@ResponseBody
	public JsonMsg addResources(int id, int[] reIds) {
//		String roleIdStr = this.request.getParameter("id");
//		String reIds = this.request.getParameter("reIds");
//		if (roleIdStr == null || roleIdStr.length() == 0) {
//			throw new SysException();
//		}
//
//		Long[] _reIds = null;
//		if (reIds != null && reIds.length() != 0) {
//			String[] reIdsArray = reIds.split(",");
//			_reIds = new Long[reIdsArray.length];
//			for (int i = 0; i < reIdsArray.length; i++) {
//				_reIds[i] = Long.parseLong(reIdsArray[i]);
//			}
//		} else {
//			_reIds = new Long[] {};
//		}

//		Long roleId = Long.parseLong(roleIdStr);

		try {
			roleService.bindResources(id, reIds);
			return new JsonMsg(true, "添加资源成功");
		} catch (RoleNotFoundException e) {
			e.printStackTrace();
			return new JsonMsg(false, "添加资源失败");
		}


	}

}
