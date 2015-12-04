package com.rying.service.init;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.rying.entity.Menu;
import com.rying.entity.SysUser;
import com.rying.service.IAdminService;
import com.rying.service.IMenuService;
import com.rying.service.ISysUserService;

@Service
public class InitService implements ApplicationListener<ContextRefreshedEvent> {
	private Logger log = Logger.getLogger(InitService.class);

	@Resource
	private IMenuService menuService;
	@Resource
	private IAdminService adminService;
	@Resource
	private ISysUserService sysUserService;

	private static final String XML_PATH = "/sys_menu.xml";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			initSysUser();
			log.info("初始化系统管理员完成");

			initSysMenu();
			log.info("初始化系统菜单完成");
		}
	}

	private void initSysUser() {
		SysUser admin = this.adminService.getAdminFromConfig();
		if (!sysUserService.exist(admin)) {
			this.sysUserService.addSysUser(admin);
		}
	}

	private void initSysMenu() {
		File xml = this.getXmlFile();
		List<Menu> menus = this.parseXml(xml);

		insertMenu(menus);
	}

	private void insertMenu(List<Menu> menus) {
		for (Menu m : menus) {
			insertMenu(m);
		}
	}

	private void insertMenu(Menu menu) {
		boolean exist = this.menuService.exist(menu);
		if (!exist) {
			Menu parent = menu.getMenu();
			if (parent != null) {
				boolean parentExist = this.menuService.exist(parent);
				if (!parentExist) {
					this.menuService.add(parent);
				}
			}
			this.menuService.add(menu);
		}
	}

	private List<Menu> parseXml(File file) {
		List<Menu> result = new ArrayList<>();
		try {
			Document document = new SAXReader().read(file);
			Element root = document.getRootElement();
			Menu rootMenu = this.getMenu(root);
			result.add(rootMenu);

			@SuppressWarnings("unchecked")
			List<Element> children = root.elements("menu");
			for (Element child : children) {
				Menu m = this.getMenu(child);
				m.setMenu(rootMenu);
				result.add(m);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Menu getMenu(Element ele) {
		Menu m = new Menu();
		m.setSys(1);
		m.setName(ele.attributeValue("name"));
		m.setUrl(ele.attributeValue("url"));
		m.setSeq(Integer.parseInt(ele.attributeValue("seq")));
		m.setIconCls(ele.attributeValue("iconCls"));
		m.setDescription(ele.attributeValue("description"));
		Date now = new Date();
		m.setModifytime(now);
		m.setCreatetime(now);
		return m;
	}

	private File getXmlFile() {
		URL url = this.getClass().getResource(XML_PATH);
		log.info("sys_menu.xml文件路径 = " + url.toString());
		String fullName = url.toString().replace("file:", "");
		return new File(fullName);
	}

	public static void main(String[] args) {
		InitService service = new InitService();
		File file = service.getXmlFile();
		List<Menu> list = service.parseXml(file);
		service.insertMenu(list);
	}
}
