package com.rying.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rying.dao.IMenuDao;
import com.rying.easyui.model.ComboTree;
import com.rying.easyui.model.TreeNode;
import com.rying.entity.Menu;
import com.rying.entity.Resources;
import com.rying.exception.MenuNotFoundException;
import com.rying.util.DateUtil;
import com.rying.util.StrValidator;

@Service
public class MenuService implements IMenuService {
	private static Logger log = Logger.getLogger(MenuService.class);

	@Resource
	private IMenuDao menuDao;
	@Resource
	private IRoleService roleService;
	@Resource
	private IResourcesService resourcesService;

	public void add(Menu entity) {
		if (entity == null)
			throw new NullPointerException();

		Date now = new Date();
		entity.setCreatetime(now);
		entity.setModifytime(now);

		this.menuDao.save(entity);
	}

	public void delete(Menu entity) {
		if (entity == null)
			throw new NullPointerException("entity is null");
		if (entity.getId() == 0)
			throw new NullPointerException("menuId is 0");

		Set<Resources> reSet = entity.getResources();
		if (!reSet.isEmpty()) {
			// logger.info("级联删除菜单下的资源");
			for (Resources re : reSet) {
				resourcesService.delete(re);
			}
		}

		entity.setMenu(null);
		menuDao.delete(entity);
	}

	public void deleteById(int id) throws MenuNotFoundException {
		Menu entity = menuDao.getById(id);
		if (entity == null)
			throw new MenuNotFoundException();

		Set<Menu> menuSet = entity.getChildren();
		if (menuSet.isEmpty()) {
			delete(entity);
		} else {
			for (Menu menu : menuSet) {
				delete(menu);
			}
		}
	}

	public void updateMenu(Menu entity) {
		if (entity == null)
			throw new NullPointerException();
		if (entity.getId() == 0)
			throw new NullPointerException("menuId is 0");

		entity.setModifytime(DateUtil.getCurrentDate());
		menuDao.update(entity);
	}

	@Override
	public void updateBaseProperies(Menu menu) throws MenuNotFoundException {
		Menu entity = getMenuById(menu.getId());
		if (entity == null) {
			throw new MenuNotFoundException();
		}

		entity.setName(menu.getName());
		entity.setMenu(menu.getMenu());
		entity.setDescription(menu.getDescription());
		entity.setSeq(menu.getSeq());
		entity.setUrl(menu.getUrl());
		entity.setIconCls(menu.getIconCls());

		this.updateMenu(entity);
	}

	public Menu getMenuById(int id) {
		return menuDao.getById(id);
	}

	/**
	 * 拉取菜单列表
	 */
	public List<Menu> findMenuByPid(String id) {
		StringBuffer sb = new StringBuffer();
		if (id != null && id.length() > 0) {
			sb.append("from Menu as menu where menu.menu.id=");
			sb.append(id);
		} else {
			sb.append("from Menu as menu where menu.menu is null");
		}
		sb.append(" order by menu.seq");

		List<Menu> menuList = this.menuDao.queryListByHQL(sb.toString());

		return menuList;
	}

	/**
	 * 获取系统菜单树
	 */
	public List<TreeNode> findSysMenuTree() {
		List<Menu> roots = this.findSysRoots();
		List<TreeNode> treeList = MenuConverter.MenuList2TreeNodeList(roots);
		return treeList;
	}
	
	public List<Menu> findSysRoots() {
		String hql = "from Menu as entity where entity.sys = 1 and entity.menu is null order by entity.seq";
		log.info("hql = " + hql);
		
		List<Menu> result = this.menuDao.queryListByHQL(hql);
		return result;
	}

	@Override
	public List<Menu> treegrid(String order, String sort) {
		List<Menu> menuList = findMenuListOfTop(order, sort);
		return menuList;
	}

	/**
	 * 取根菜单，以属性排序
	 * 
	 * @return
	 */
	private List<Menu> findMenuListOfTop(String order, String sort) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Menu as entity where entity.menu is null and entity.sys = 0 order by entity.seq asc");
		if (order != null && order.length() > 0 && sort != null && sort.length() > 0) {
			sb.append(",").append(sort).append(" ").append(order);
		}
		List<Menu> list = this.menuDao.queryListByHQL(sb.toString());
		return list;
	}

	/**
	 * 取根菜单，以seq排序
	 * 
	 * @return
	 */
	public List<Menu> findMenuListOfTop() {
		String hql = "from Menu as menu where menu.menu is null and menu.sys = 0 order by seq";
		return this.menuDao.queryListByHQL(hql);
	}

	@Override
	public List<ComboTree> findAllMenuForComboboxTree() {
		List<Menu> list = findMenuListOfTop();

		List<ComboTree> reList = new ArrayList<ComboTree>();
		for (Menu menu : list) {
			ComboTree cbTree = Menu2ComboboxTree(menu);
			reList.add(cbTree);
		}
		return reList;
	}

	private ComboTree Menu2ComboboxTree(Menu entity) {
		Set<Menu> children = entity.getChildren();

		List<ComboTree> list = new ArrayList<ComboTree>();
		for (Menu child : children) {
			ComboTree cbbt = Menu2ComboboxTree(child);
			list.add(cbbt);
		}

		ComboTree cbTree = new ComboTree();
		cbTree.setId(String.valueOf(entity.getId()));
		cbTree.setText(entity.getName());
		cbTree.setState(ComboTree.STATE_OPEN);
		cbTree.setIconCls(entity.getIconCls());
		cbTree.setChildren(list);

		return cbTree;
	}

	@Override
	public List<TreeNode> findFuncMenuTreeByUserId(int userId) {
		if (userId == 0)
			throw new NullPointerException("userId is null");

		List<Resources> reList = roleService.findResourcesBySysUserId(userId);

		if (reList.isEmpty()) {
			return new ArrayList<TreeNode>();
		}

		List<Menu> menuList = new ArrayList<Menu>();
		for (Resources re : reList) {
			Menu m = re.getUpperMenu();
			if (!menuList.contains(m)) {
				Menu menu = getMenuById(m.getId());
				menuList.add(menu);
			}
		}
		List<Menu> resultMenuList = findPMenusAndRemoveBrotherMenus(menuList);

		return MenuConverter.MenuList2TreeNodeList(resultMenuList);
	}

	/**
	 * 递归查找父级菜单，并且删除其它兄弟菜单
	 * 
	 * @param
	 * @return
	 */
	private List<Menu> findPMenusAndRemoveBrotherMenus(List<Menu> menus) {
		if (menus == null)
			throw new NullPointerException();
		if (menus.isEmpty())
			return menus;// menus如果为empty，直接返回。避免递归死循环

		// 拿到所有菜单的父级菜单，放到一个List里
		List<Menu> pMenus = new ArrayList<Menu>();
		for (Menu menu : menus) {
			Menu pMenu = menu.getMenu();
			if (pMenu == null) {// 结果条件：如果父级菜单为Null,说明是根菜单。返回
				return menus;
			}
			if (!pMenus.contains(pMenu)) {
				pMenus.add(pMenu);
			}
		}

		// 循环所有的父级菜单，取出所有的子菜单，删除兄弟菜单
		for (Menu pMenu : pMenus) {
			Set<Menu> children = pMenu.getChildren();

			Iterator<Menu> it = children.iterator();
			while (it.hasNext()) {
				Menu m = it.next();
				if (!menus.contains(m)) {
					it.remove();
				}
			}
		}

		return findPMenusAndRemoveBrotherMenus(pMenus);
	}

	@Override
	public List<TreeNode> findAllFuncMenuTree() {
		List<Menu> allMenu = findMenuListOfTop();
		return MenuConverter.MenuList2TreeNodeList(allMenu);
	}

	public static void main(String[] args) {
		new MenuService().findPMenusAndRemoveBrotherMenus(new ArrayList<Menu>());
	}

	@Override
	public boolean exist(Menu menu) {
		// TODO Auto-generated method stub
		int id = menu.getId();
		// ID存在，以ID去检查
		if (id != 0) {
			return exist(id);
		}
		String name = menu.getName();
		if (StrValidator.isNotNullAndEmpty(name)) {
			return exist(name);
		}
		return false;
	}

	public boolean exist(String name) {
		Menu menu = this.getMenuByName(name);
		return menu != null;
	}

	public Menu getMenuByName(String name) {
		String hql = "from Menu as m where m.name = ?";
		log.info("hql = " + hql);

		return this.menuDao.getByHQL(hql, new Object[] { name });
	}

	public boolean exist(int id) {
		Menu menu = this.getMenuById(id);
		return menu != null;
	}
}
