package com.rying.service;

import java.util.List;

import com.rying.easyui.model.ComboTree;
import com.rying.easyui.model.TreeNode;
import com.rying.entity.Menu;
import com.rying.exception.MenuNotFoundException;

public interface IMenuService {
	public void add(Menu entity);

	public void delete(Menu entity);

	public void updateMenu(Menu entity);

	/**
	 * 系统菜单树
	 */
	public List<TreeNode> findSysMenuTree();

	/**
	 * 树开表格 并以属性排序
	 * 
	 * @return
	 */
	public List<Menu> treegrid(String order, String sort);

	/**
	 * 取所有的根菜单
	 * 
	 * @return
	 */
	public List<Menu> findMenuListOfTop();

	/**
	 * 删除 by ID
	 * 
	 * @param id
	 */
	public void deleteById(int id) throws MenuNotFoundException;

	/**
	 * 取所有菜单，以“List<“ComboboxTree”>”返回
	 * 
	 * @return
	 */
	public List<ComboTree> findAllMenuForComboboxTree();

	/**
	 * 取用户拥有的菜单树
	 * 
	 * @param id
	 * @return
	 */
	public List<TreeNode> findFuncMenuTreeByUserId(int id);

	/**
	 * 取所有的菜单树
	 * 
	 * @return
	 */
	public List<TreeNode> findAllFuncMenuTree();

	/**
	 * 手动更新基本属性
	 * 
	 * @param menu
	 */
	public void updateBaseProperies(Menu menu) throws MenuNotFoundException;

	/**
	 * 菜单是否存在
	 * 
	 * @param m
	 * @return
	 */
	public boolean exist(Menu menu);
	
	public boolean exist(String name);
}
