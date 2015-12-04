package com.rying.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rying.dao.IResourcesDao;
import com.rying.easyui.model.TreeNode;
import com.rying.entity.Menu;
import com.rying.entity.Resources;
import com.rying.entity.Role;
import com.rying.exception.ResourcesNotFoundException;
import com.rying.model.ResourcesTreeGrid;
import com.rying.util.DateUtil;

@Service
public class ResourcesService implements IResourcesService {
	private Logger logger = Logger.getLogger(ResourcesService.class);

	@Resource
	private IResourcesDao resourcesDao;
	@Resource
	private IMenuService menuService;

	@Override
	public Resources getByPk(int id) {
		return resourcesDao.getById(id);
	}

	@Override
	public void add(Resources entity) {
		if (entity == null)
			throw new NullPointerException();

		Date currentDate = DateUtil.getCurrentDate();
		entity.setCreatetime(currentDate);
		entity.setModifytime(currentDate);

		resourcesDao.save(entity);
	}

	@Override
	public void delete(Resources entity) {
		if (entity == null)
			return;

		Set<Role> roles = entity.getRoles();
		if (roles != null) {
			for (Role role : roles) {
				role.getResources().remove(entity);
			}
		}

		resourcesDao.delete(entity);
	}

	public void deleteById(int id) throws ResourcesNotFoundException {
		Resources entity = getByPk(id);
		if (entity == null)
			throw new ResourcesNotFoundException();

		Set<Role> roleSet = entity.getRoles();
		for (Role role : roleSet) {
			role.getResources().remove(entity);
		}

		entity.setRoles(null);
		entity.setUpperMenu(null);

		delete(entity);
	}

	@Override
	public void update(Resources resources) {
		if (resources != null && resources.getId() != 0) {
			Resources entity = this.getByPk(resources.getId());

			entity.setModifytime(new Date());
			entity.setDescription(resources.getDescription());
			entity.setUpperMenu(resources.getUpperMenu());
			entity.setSeq(resources.getSeq());
			entity.setName(resources.getName());
			entity.setDescription(resources.getDescription());
			entity.setAction(resources.getAction());
			resourcesDao.update(entity);
		}
	}

	@Override
	public List<Resources> findAll() {
		return resourcesDao.queryAll();
	}

	@Override
	public List<ResourcesTreeGrid> treegrid() {
		List<Menu> menuList = this.menuService.findMenuListOfTop();

		List<ResourcesTreeGrid> resultList = new ArrayList<ResourcesTreeGrid>();
		for (Menu menu : menuList) {
			ResourcesTreeGrid rtg = Menu2ResourcesTreeGrid(menu);
			resultList.add(rtg);
		}

		return resultList;
	}

	@Override
	public List<TreeNode> tree() {
		List<ResourcesTreeGrid> list = this.treegrid();
		return resourcesTreeGridList2TreeNodeList(list);
	}

	private List<TreeNode> resourcesTreeGridList2TreeNodeList(List<ResourcesTreeGrid> list) {
		if (list == null)
			throw new NullPointerException();
		if (list.isEmpty()) {
			return new ArrayList<TreeNode>();
		}

		List<TreeNode> resultList = new ArrayList<TreeNode>();
		for (ResourcesTreeGrid rtg : list) {
			TreeNode tn = resourcesTreeGrid2TreeNode(rtg);
			resultList.add(tn);
		}

		return resultList;
	}

	private TreeNode resourcesTreeGrid2TreeNode(ResourcesTreeGrid rtg) {
		logger.info("将ResourcesTreeGrid对象递归成TreeNode对象。。。");
		if (rtg == null)
			throw new NullPointerException();

		List<ResourcesTreeGrid> chilren = rtg.getChildren();

		List<TreeNode> tnList = new ArrayList<TreeNode>();
		for (ResourcesTreeGrid child : chilren) {
			TreeNode tn = resourcesTreeGrid2TreeNode(child);
			tnList.add(tn);
		}

		TreeNode tn = new TreeNode();
		if (ResourcesTreeGrid.TYPE_FUNC.equals(rtg.getType())) {
			tn.setId(String.valueOf(rtg.getId()));
		}
		tn.setText(rtg.getName());
		tn.setState(TreeNode.STATE_OPEN);
		// tn.setChecked(true);
		tn.setIconCls(rtg.getIconCls());
		tn.setChildren(tnList);

		return tn;
	}

	/**
	 * 菜单转成ResourcesTreeGrid的一个vo对象
	 * 
	 * @param entity
	 * @return
	 */
	private ResourcesTreeGrid Menu2ResourcesTreeGrid(Menu entity) {
		logger.info("将Menu递归成ResourcesTreeGrid对象。。。");
		// 拿到子菜单
		Set<Menu> children = entity.getChildren();

		List<ResourcesTreeGrid> rtgList = new ArrayList<ResourcesTreeGrid>();
		if (children.isEmpty()) { // 子菜单为空，去找下级资源
			Set<Resources> reSet = entity.getResources();

			// 把下级资源转换成菜单
			for (Resources re : reSet) {
				Menu menu = new Menu();

				menu.setId(re.getId());
				menu.setUrl(re.getAction());
				menu.setCreatetime(re.getCreatetime());
				menu.setDescription(re.getDescription());
				menu.setIconCls(re.getIconCls());
				menu.setModifytime(re.getModifytime());
				menu.setName(re.getName());
				menu.setSeq(re.getSeq());
				menu.setMenu(re.getUpperMenu());
				menu.setType(re.getType());

				ResourcesTreeGrid rtg = Menu2ResourcesTreeGrid(menu);
				rtgList.add(rtg);
			}
		} else { // 转所有的子菜单
			for (Menu child : children) {
				ResourcesTreeGrid rtg = Menu2ResourcesTreeGrid(child);
				rtgList.add(rtg);
			}
		}

		ResourcesTreeGrid result = new ResourcesTreeGrid();

		result.setId(entity.getId());
		result.setAction(entity.getUrl());
		result.setCreatetime(entity.getCreatetime());
		result.setDescription(entity.getDescription());
		result.setIconCls(entity.getIconCls());
		result.setModifytime(entity.getModifytime());
		result.setName(entity.getName());
		result.setSeq(entity.getSeq());
		result.setType(entity.getType());
		result.setChildren(rtgList);

		// 保存与低级的关系(保存pId)
		if (entity.getMenu() != null) {
			result.setpId(entity.getMenu().getId());
		}

		return result;
	}

}
