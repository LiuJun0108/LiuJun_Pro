package com.rying.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rying.easyui.model.Datagrid;
import com.rying.easyui.model.JsonMsg;
import com.rying.entity.SysUser;
import com.rying.exception.SysUserNotFoundException;
import com.rying.model.SysUserQueryVo;
import com.rying.service.ISysUserService;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	@Resource
	private ISysUserService sysUserService;

	@RequestMapping("/datagrid")
	@ResponseBody
	public Datagrid<SysUser> datagrid(int page, int rows, String order,
			String sort, SysUser sysUser, String startModifyTime,
			String endModifyTime, String startCreateTime, String endCreateTime) {
		SysUserQueryVo susc = new SysUserQueryVo(sysUser.getUsername(),
				sysUser.getName(), sysUser.getPhone(), sysUser.getEmail(),
				startModifyTime, endModifyTime, startCreateTime, endCreateTime);

		Datagrid<SysUser> datagrid = this.sysUserService.datagrid(susc, order,
				sort, page, rows);
		return datagrid;
	}

	@RequestMapping("/addSysUser")
	@ResponseBody
	public JsonMsg addSysUser(SysUser sysUser) {
		try {
			sysUserService.addSysUser(sysUser);
			return new JsonMsg(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonMsg(false, "保存失败");
		}
	}

	@RequestMapping("/updateSysUser")
	@ResponseBody
	public JsonMsg updateSysUser(SysUser sysUser) {
		try {
			sysUserService.update(sysUser);
			Map<String, String> re = new HashMap<String, String>(1);
			re.put("flag", "update");
			return new JsonMsg(true, "修改成功", re);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonMsg(false, "修改失败");
		}
	}

	@RequestMapping("/destroySysUser")
	@ResponseBody
	public JsonMsg destroySysUser(int id) {
		try {
			sysUserService.deleteById(id);
			return new JsonMsg(true, "删除成功");
		} catch (SysUserNotFoundException e) {
			return new JsonMsg(false, "删除失败");
		}

	}

	@RequestMapping("/addRoles")
	@ResponseBody
	public JsonMsg addRoles(int id, int[] roleIds) {
		try {
			sysUserService.addRole(id, roleIds);
			return new JsonMsg(true, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonMsg(false, "添加失败");
		}
	}

	@RequestMapping("/selectSysUser")
	public String selectSysUser() {
		return "sys/selectSysUser";
	}
}
