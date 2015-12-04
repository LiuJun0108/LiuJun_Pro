package com.rying.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.rying.dto.MenuDatagrid;
import com.rying.easyui.model.TreeNode;
import com.rying.entity.Menu;
import com.rying.service.comparator.MenuComparator;

public class MenuConverter {
	public static MenuDatagrid Menu2MenuDataGrid(Menu menu) {
		Menu entity = new Menu();
		BeanUtils.copyProperties(menu, entity);

		int id = menu.getId();
		String name = menu.getName();
		String url = menu.getUrl();
		int seq = menu.getSeq();
		String iconCls = menu.getIconCls();
		String description = menu.getDescription();
		Date modifytime = menu.getModifytime();
		Date createtime = menu.getCreatetime();
		int pid = 0;
		if (menu.getMenu() != null) {
			pid = menu.getMenu().getId();
		}
		List<MenuDatagrid> children = new ArrayList<MenuDatagrid>();

		Set<Menu> menuList = entity.getChildren();
		for (Menu _menu : menuList) {
			MenuDatagrid mdg = Menu2MenuDataGrid(_menu);
			children.add(mdg);
		}

		MenuDatagrid result = new MenuDatagrid();
		result.setId(id);
		result.setChildren(children);
		result.setCreatetime(createtime);
		result.setDescription(description);
		result.setIconCls(iconCls);
		result.setModifytime(modifytime);
		result.setPid(pid);
		result.setName(name);
		result.setSeq(seq);
		result.setUrl(url);

		return result;
	}

	public static List<MenuDatagrid> MenuList2MenuDatagridList(List<Menu> menuList) {
		List<MenuDatagrid> resultList = new ArrayList<MenuDatagrid>();

		for (Menu menu : menuList) {
			MenuDatagrid mdg = Menu2MenuDataGrid(menu);
			resultList.add(mdg);
		}
		return resultList;
	}

	/**
	 * 
	 * MenuList to treeNodeList
	 * 
	 * @param entityList
	 *            a list of menu
	 * @return
	 */
	public static List<TreeNode> MenuList2TreeNodeList(List<Menu> entityList) {
		if (entityList == null)
			throw new NullPointerException();

		// 按seq排序
		MenuComparator mc = new MenuComparator();
		Collections.sort(entityList, mc);

		List<TreeNode> list = new ArrayList<TreeNode>();

		for (Menu entity : entityList) {
			TreeNode node = Menu2TreeNode(entity);
			list.add(node);
		}

		return list;
	}

	/**
	 * 
	 * Menu to treeNode
	 * 
	 * @param entity
	 *            menu
	 * @return
	 */
	public static TreeNode Menu2TreeNode(Menu entity) {
		if (entity == null)
			throw new NullPointerException();

		TreeNode node = new TreeNode();
		node.setState("open");
		node.setChecked(false);

		node.setText(entity.getName());
		node.setIconCls(entity.getIconCls());

		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("url", entity.getUrl());
		node.setAttributes(attributes);

		Set<Menu> childrenMenu = entity.getChildren();
		// 按seq排序
		List<Menu> temp_childrenMenu = new ArrayList<Menu>(childrenMenu);
		MenuComparator mc = new MenuComparator();
		Collections.sort(temp_childrenMenu, mc);

		List<TreeNode> children = new ArrayList<TreeNode>();// 子节点
		for (Menu childMenu : temp_childrenMenu) {
			TreeNode childTree = Menu2TreeNode(childMenu);
			children.add(childTree);
		}

		node.setChildren(children);

		return node;
	}
}
