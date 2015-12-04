package com.rying.service;

import java.util.List;

import com.rying.easyui.model.TreeNode;
import com.rying.entity.Resources;
import com.rying.exception.ResourcesNotFoundException;
import com.rying.model.ResourcesTreeGrid;


public interface IResourcesService {
	public List<Resources> findAll();

	public List<ResourcesTreeGrid> treegrid();

	public void deleteById(int id) throws ResourcesNotFoundException;

	public List<TreeNode> tree();

	public Resources getByPk(int id);

	public void add(Resources entity);

	public void delete(Resources entity);

	public void update(Resources entity);
}
