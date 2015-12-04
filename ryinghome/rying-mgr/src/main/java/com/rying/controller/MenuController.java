package com.rying.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rying.dto.MenuDatagrid;
import com.rying.easyui.model.ComboTree;
import com.rying.easyui.model.Combobox;
import com.rying.easyui.model.JsonMsg;
import com.rying.easyui.model.TreeNode;
import com.rying.entity.Menu;
import com.rying.entity.SysUser;
import com.rying.exception.MenuNotFoundException;
import com.rying.service.IMenuService;
import com.rying.service.MenuConverter;

@Controller
@RequestMapping("/menu")
@SessionAttributes({ "currentUser" })
public class MenuController {
	@Resource
	private IMenuService menuService;

	@RequestMapping("/menu")
	public String menu() {
		return "sys/menu";
	}

	@RequestMapping("/resources")
	public String resources() {
		return "sys/resources";
	}

	@RequestMapping("/sysUser")
	public String sysUser() {
		return "sys/sysUser";
	}

	@RequestMapping("/role")
	public String role() {
		return "sys/role";
	}

	@RequestMapping("/listSysMenu")
	@ResponseBody
	public List<TreeNode> listSysMenu(@ModelAttribute("currentUser") SysUser sysUser) {
		List<TreeNode> treeList2 = this.menuService.findSysMenuTree();

		return treeList2;
	}

	@RequestMapping("/listUserFuncMenu")
	@ResponseBody
	public List<TreeNode> listUserFuncMenu(@ModelAttribute("currentUser") SysUser sysUser) {
		return menuService.findFuncMenuTreeByUserId(sysUser.getId());
	}

	@RequestMapping("/treegrid")
	@ResponseBody
	public List<MenuDatagrid> treegrid(String order, String sort) {

		List<Menu> menuList = this.menuService.treegrid(order, sort);

		List<MenuDatagrid> dategridList = MenuConverter.MenuList2MenuDatagridList(menuList);

		return dategridList;
	}

	@RequestMapping("/rootMenu")
	@ResponseBody
	public List<Combobox> rootMenu() {
		List<Menu> menuList = menuService.findMenuListOfTop();

		// 转成Combobox，方便页面展现
		List<Combobox> cList = new ArrayList<Combobox>();
		for (Menu menu : menuList) {
			Combobox cb = new Combobox();
			cb.setId(String.valueOf(menu.getId()));
			cb.setText(menu.getName());

			cList.add(cb);
		}
		return cList;
	}

	@RequestMapping("/addMenu")
	@ResponseBody
	public JsonMsg addMenu(@RequestParam(defaultValue = "0") int pid, Menu menu) {
		if (pid != 0) {
			Menu parentMenu = new Menu();
			parentMenu.setId(pid);
			menu.setMenu(parentMenu);
		}

		menuService.add(menu);
		return new JsonMsg(true, "保存成功");
	}

	@RequestMapping("/updateMenu")
	@ResponseBody
	public JsonMsg updateMenu(@RequestParam(defaultValue = "0") int pid, Menu menu) {
		if (pid != 0) {
			Menu parentMenu = new Menu();
			parentMenu.setId(pid);
			menu.setMenu(parentMenu);
		}

		try {
			menuService.updateBaseProperies(menu);

			Map<String, String> re = new HashMap<String, String>(1);
			re.put("flag", "update");
			JsonMsg jsonMsg = new JsonMsg(true, "修改成功");
			jsonMsg.setObj(re);
			return jsonMsg;
		} catch (MenuNotFoundException e) {
			return new JsonMsg(false, "更新菜单不存在");
		}

	}

	@RequestMapping("/destroyMenu")
	@ResponseBody
	public JsonMsg destroyMenu(int id) {
		try {
			menuService.deleteById(id);
			return new JsonMsg(true, "删除成功");
		} catch (MenuNotFoundException e) {
			return new JsonMsg(true, "删除失败");
		}

	}

	@RequestMapping("/comboboxTreeAllMenu")
	@ResponseBody
	public List<ComboTree> comboboxTreeAllMenu() {
		List<ComboTree> list = menuService.findAllMenuForComboboxTree();
		return list;
	}
}
