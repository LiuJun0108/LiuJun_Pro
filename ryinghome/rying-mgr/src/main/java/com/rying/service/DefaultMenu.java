package com.rying.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.rying.easyui.model.IconCls;
import com.rying.entity.Menu;
import com.rying.util.DateUtil;


/**
 * 系统默认菜单/系统菜单
 * 
 * @author LiuJun
 * 
 */
public class DefaultMenu {
	/**
	 * 系统的默认菜单
	 * 
	 * @return
	 */
	public static List<Menu> getDefaultMenu() {
		Date currentDate = DateUtil.getCurrentDate();
		Menu menu1 = createMenuByProperties("菜单管理", "menu/menu.do", 0, IconCls.ICON_OK.toString(), "菜单管理", currentDate, currentDate);
		Menu menu2 = createMenuByProperties("资源管理", "menu/resources.do", 1, IconCls.ICON_OK.toString(), "资源管理", currentDate, currentDate);
		Menu menu3 = createMenuByProperties("系统用户管理", "menu/sysUser.do", 3, IconCls.ICON_OK.toString(), "系统用户管理", currentDate, currentDate);
		Menu menu4 = createMenuByProperties("角色管理", "menu/role.do", 2, IconCls.ICON_OK.toString(), "角色管理", currentDate, currentDate);

		Menu menuBar = createMenuByProperties("系统管理", "", 0, IconCls.ICON_OK.toString(), "系统管理", currentDate, currentDate);
		menuBar.getChildren().add(menu1);
		menuBar.getChildren().add(menu2);
		menuBar.getChildren().add(menu3);
		menuBar.getChildren().add(menu4);

		List<Menu> list = new ArrayList<Menu>(Arrays.asList(new Menu[] { menuBar }));

		return list;
	}

	private static Menu createMenuByProperties(String name, String url, int seq, String iconCls, String description, Date modifytime, Date createtime) {
		Menu entity = new Menu();
		entity.setName(name);
		entity.setUrl(url);
		entity.setSeq(seq);
		entity.setIconCls(iconCls);
		entity.setDescription(description);
		entity.setModifytime(modifytime);
		entity.setCreatetime(createtime);

		return entity;
	}

}
