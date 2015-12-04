package com.rying.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rying.easyui.model.TreeNode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring*.xml" })
public class MenuTest {
	@Resource
	private IMenuService menuService;

	@Test
	public void testFindSysMenuTree() {
		List<TreeNode> tnList = this.menuService.findSysMenuTree();
	}
	
}
