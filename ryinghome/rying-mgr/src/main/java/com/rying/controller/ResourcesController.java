package com.rying.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rying.easyui.model.JsonMsg;
import com.rying.easyui.model.TreeNode;
import com.rying.entity.Menu;
import com.rying.entity.Resources;
import com.rying.exception.ResourcesNotFoundException;
import com.rying.model.ResourcesTreeGrid;
import com.rying.service.IResourcesService;

@Controller
@RequestMapping("/resources")
public class ResourcesController {
	@Resource
	private IResourcesService resourcesService;

	@RequestMapping("/treegrid")
	@ResponseBody
	public List<ResourcesTreeGrid> treegrid() {
		List<ResourcesTreeGrid> list = resourcesService.treegrid();
		return list;
	}

	@RequestMapping("/tree")
	@ResponseBody
	public List<TreeNode> tree() {
		List<TreeNode> list = resourcesService.tree();
		return list;
	}

	@RequestMapping(value = "/addResources", method = RequestMethod.GET)
	public String addResources() {
		return "sys/addResources";
	}

	@RequestMapping("/addResources")
	@ResponseBody
	public JsonMsg addResources(@RequestParam(defaultValue = "0") int pid, Resources resources) {
		if (pid != 0) {
			Menu pMenu = new Menu();
			pMenu.setId(pid);
			resources.setUpperMenu(pMenu);
		}

		resourcesService.add(resources);
		return new JsonMsg(true, "保存成功");
	}

	@RequestMapping("/updateResources")
	@ResponseBody
	public JsonMsg updateResources(@RequestParam(defaultValue = "0") int pid, Resources resources) {
		if (pid != 0) {
			Menu pMenu = new Menu();
			pMenu.setId(pid);
			resources.setUpperMenu(pMenu);
		}

		this.resourcesService.update(resources);

		Map<String, String> resultMap = new HashMap<String, String>(1);
		resultMap.put("flag", "update");

		JsonMsg jsonMsg = new JsonMsg(true, "修改成功");
		jsonMsg.setObj(resultMap);

		return jsonMsg;
	}

	@RequestMapping("/destroyResources")
	@ResponseBody
	public JsonMsg destroyResources(int id) {
		try {
			resourcesService.deleteById(id);
			return new JsonMsg(true, "删除成功");
		} catch (ResourcesNotFoundException e) {
			return new JsonMsg(false, "删除失败");
		}
	}

}
